/*
*Script de creación y carga de la base de datos
*/

print("STARTING SCRIPT");

//Host de la base de datos
conn = new Mongo("localhost");

//Nombre de la base de datos que se utilizará
db = conn.getDB("youtube");

//se limpia la bd por si hay datos persistentes
db.dropDatabase();



//se crean las colecciones del modelo de datos 
db.createCollection("usuarios", {
	validator:{
		$jsonSchema: {
			bsonType: "object",
			required:["email", "password","nombre"],
			properties:{
				email: {
					bsonType: "string",
					uniqueItems:true
				},
				password: {
					bsonType: "string"
				},
				nombre: {
					bsonType: "string"
				},
				fechaNac: {
					bsonType: "date"
				},
				sexo: {
					enum: ["H","M","I"]
				},
				pais: {
					bsonType: "string"
				},
				cp: {
					bsonType: "string"
				},
				videos: {
					bsonType: "array",
					uniqueItems: true,
					items: {
						bsonType: "string"
					}
				},
				playlists: {
					bsonType: "array",
					uniqueItems: true,
					items: {
						bsonType: "object",
						required: ["list_id", "email","nombreLista", "date","estado"],
						properties: {
							list_id: {
								bsonType: "objectId",
							},
							email:{
								bsonType: "string",
							},
							nombreLista: {
								bsonType: "string",
							},
							date: {
								bsonType: "date"
							},
							estado: {
								enum: ["publica","privada"]
							}
						}
					}
				},
				canales: {
					bsonType: "array",
					uniqueItems: true,
					items: {
						bsonType: "object",
						required: ["canal_id", "nombre"],
						properties: {
							canal_id: {
								bsonType: "objectId",
							},
							nombre: {
								bsonType: "string",
							},
							descripcion: {
								bsonType: "string"
							},
							suscriptores: {
								bsonType: "array",
								uniqueItems: true,
								items:{
									bsonType: "string"
								}
							}
						}
					}
				}
			}
		}
	}
});

db.createCollection("videos", {
	validator:{
		$jsonSchema: {
			bsonType: "object",
			required:["emailUsuario", "publicado","titulo", "estado"],
			properties:{
				emailUsuario: {
					bsonType: "string"
				},
				publicado: {
					bsonType: "date"
				},
				titulo: {
					bsonType: "string"
				},
				descripcion: {
					bsonType: "string"
				},
				size: {
					bsonType: "double",
					description: "tamaño del video en megabytes"
				},
				archivo: {
					bsonType: "string"
				},
				duracion: {
					bsonType: "double",
					description: "duración del video en minutos"
				},
				thumbnail: {
					bsonType: "string",
					description:"ruta del thumbnail"
				},
				reproducciones: {
					bsonType: "double",
				},
				estado: {
					enum: ["publico","oculto","privado"]
				},
				likes: {
					bsonType: "array",
					uniqueItems: true,
					items: {
						bsonType: "object",
						required: ["email", "date"],
						properties: {
							email: {
								bsonType: "string",
							},
							date: {
								bsonType: "date"
							}
						}
					}
				},
				dislikes: {
					bsonType: "array",
					uniqueItems: true,
					items: {
						bsonType: "object",
						required: ["email", "date"],
						properties: {
							email: {
								bsonType: "string",
							},
							date: {
								bsonType: "date"
							}
						}
					}
				},
				tags: {
					bsonType: "array",
					uniqueItems: true,
					items: {
						bsonType: "object",
						required: ["tag_id", "tag"],
						properties: {
							tag_id: {
								bsonType: "objectId",
							},
							tag: {
								bsonType: "string",
							}
						}
					}
				},
				comentarios: {
					bsonType: "array",
					items: {
						bsonType: "object",
						required: ["comment_id", "emailUsuario", "texto", "date"],
						properties: {
							comment_id: {
								bsonType: "objectId"
							},
							emailUsuario: {
								bsonType: "string"
							},
							texto: {
								bsonType: "string"
							},
							date: {
								bsonType: "date"
							},
							likes: {
								bsonType: "array",
								uniqueItems: true,
								items: {
									bsonType: "object",
									required: ["email", "date"],
									properties: {
										email: {
											bsonType: "string",
										},
										date: {
											bsonType: "date"
										}
									}
								}
							},
							dislikes: {
								bsonType: "array",
								uniqueItems: true,
								items: {
									bsonType: "object",
									required: ["email", "date"],
									properties: {
										email: {
											bsonType: "string",
										},
										date: {
											bsonType: "date"
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
});

//se crean ususarios (documentos de la colección usuarios)
print("*************creating users*************");
db.usuarios.insertMany([
	{
	"email": "email@usuario.com","password": "012345678","nombre": "nombreUsuario",
  	"fechaNac": new Date("1981-05-20"),"sexo": "M","pais": "España","cp": "08001",
  	"videos": ["tituloVideo"],
  	"playlists": [{"list_id": new ObjectId(), "email":"email@usuario.com","nombreLista": "nombrePlaylist","date": new Date(),"estado": "publica"}],
  	"canales": [{"canal_id": new ObjectId(),"nombre": "nombreDelCanal","descripcion": "descripcion del canal","suscriptores": ["email@OtroUsuario.com","email@tercerUsuario.com"]}]
	},
	{
	"email": "email@otroUsuario.com","password": "00000000","nombre": "otroUsuario",
  	"fechaNac": new Date("1991-12-20"),"sexo": "M","pais": "España","cp": "08001",
  	"videos": ["tituloTercerVideo"],
	},
	{
		"email": "email@tercerUsuario.com","password": "11111111","nombre": "tercerUsuario",
  	"fechaNac": new Date("1987-02-05"),"sexo": "H","pais": "España","cp": "08001",
  	"videos": ["tituloOtroVideo"],
  	"playlists": [{"list_id": new ObjectId(), "email":"email@tercerUsuario.com","nombreLista": "nombreOtraPlaylist","date": new Date(),"estado": "privada"}]
  	}
]);

//se crean videos (documentos de la colección videos)
print("*************creating videos*************");
db.videos.insertMany([
	{
 	"emailUsuario": "email@usuario.com","publicado": new Date(),"titulo": "tituloVideo","descripcion": "aquí descripción video",
  	"size": 1000,"archivo": "nombreArchivoVideo","duracion": 120,"thumbnail": "ruta a thumbnail","reproducciones": 10, "estado":"publico",
  	"likes": [{"email": "email@usuario.com","date": new Date()}],
  	"dislikes": [{"email": "email@otroUsuario.com","date": new Date()}],
  	"tags": [{"tag_id": new ObjectId(),"tag": "etiqueta1"},{"tag_id": new ObjectId(),"tag": "etiqueta2"}],
  	"comentarios": [
    	{"comment_id": new ObjectId(),"emailUsuario": "email@otroUsuario.com","texto": "aquí un comentario de otroUsuario","date": new Date(),
      		"likes": [{"email": "email@usuario.com","date": new Date()}],
      		"dislikes": [{"email": "email@tercerUsuario.com","date": new Date()}]},
    	{"comment_id": new ObjectId(),"emailUsuario": "email@usuario.com","texto": "aquí respuesta de usuario","date": new Date(),
      		"likes": [{"email": "email@otroUsuario","date":new Date()}]}
 		]
	},
	{
  	"emailUsuario": "email@tercerUsuario.com","publicado": new Date(),"titulo": "tituloOtroVideo","descripcion": "aquí descripción video",
  	"size": 840,"archivo": "nombreArchivoOtroVideo","duracion": 60,"thumbnail": "ruta a thumbnail","reproducciones": 8, "estado":"publico",
  	"likes": [{"email": "email@usuario.com","date": new Date()},{"email": "email@otroUsuario.com","date":new Date(),}],
 	"tags": [{"tag_id": new ObjectId(),"tag": "etiqueta3"}],
	"comentarios": [
    	{"comment_id": new ObjectId(),"emailUsuario": "email@usuario.com","texto": "aquí comentario de usuario","date": new Date(),
      		"likes": [{"email": "email@otroUsuario", "date": new Date(),}]}
  		]
	},
	{
 	"emailUsuario": "email@otroUsuario.com","publicado": new Date(),"titulo": "tituloTercerVideo","descripcion": "aquí descripción video",
  	"size": 400,"archivo": "nombreArchivoTercerVideo","duracion": 45,"thumbnail": "ruta a thumbnail","reproducciones": 2, "estado":"privado",
  	"likes": [{"email": "email@usuario.com","date": new Date()}],
  	"dislikes": [{"email": "email@tercerUsuario.com","date": new Date()}],
  	"tags": [{"tag_id": new ObjectId(),"tag": "etiqueta4"},{"tag_id": new ObjectId(),"tag": "etiqueta5"}],
  	"comentarios": [
    	{"comment_id": new ObjectId(),"emailUsuario": "email@tercerUsuario.com","texto": "aquí un comentario de otroUsuario","date": new Date(),
      		"likes": [{"email": "email@usuario.com","date": new Date()}],
      		"dislikes": [{"email": "email@otroUsuario.com","date": new Date()}]},
    	{"comment_id": new ObjectId(),"emailUsuario": "email@usuario.com","texto": "aquí respuesta de usuario","date": new Date(),
      		"likes": [{"email": "email@otroUsuario","date":new Date()}]}
 		]
	}
	
]);

;

print("SCRIPT FINISHED");
  

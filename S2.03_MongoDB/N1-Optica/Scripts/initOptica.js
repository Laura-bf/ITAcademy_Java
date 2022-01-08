/*
*Script de creación y carga de la base de datos
*/

print("STARTING SCRIPT");

//Host de la base de datos
conn = new Mongo("localhost");

//Nombre de la base de datos que se utilizará
db = conn.getDB("optica");

//se limpia la bd por si hay datos persistentes
db.dropDatabase();

//se crean las colecciones del modelo de datos 
db.createCollection("suppliers", {
	validator:{
		$jsonSchema: {
			bsonType: "object",
			required:["_id","name", "nif","phone","fax","address"],
			properties:{
				_id: {
					bsonType: "string"
				},
				name: {
					bsonType: "string"
				},
				nif: {
					bsonType: "string",
					maxLength: 9,
					minLength: 9,
					uniqueItems: true
				},
				phone: {
					bsonType: "string",
					maxLength: 9,
					minLength: 9
				},
				fax: {
					bsonType: "string",
					maxLength: 9,
					minLength: 9
				},
				address: {
					bsonType: "object",
					required: ["cp"],
					properties: {
						street: {
							bsonType: "string"
						},
						number: {
							bsonType: "string"
						},
						floor: {
							bsonType: "string"
						},
						door: {
							bsonType: "string"
						},
						city: {
							bsonType: "string"
						},
						cp: {
							bsonType: "string",
							maxLength: 5,
							minLength: 5
						},
						country: {
							bsonType: "string"
						}
					}
				},
				brands: {
					bsonType: "array",
					uniqueItems: true
				}
			}
		}
	}
});

db.createCollection("glasses", {
	validator:{
		$jsonSchema: {
			bsonType: "object",
			required: ["_id", "brand", "graduation", "frame", "price"],
			properties: {
				_id: {
					bsonType: "string"
				},
				brand: {
					bsonType: "string"
				},
				graduation: {
					bsonType: "object",
					additionalProperties: false,
					properties: {
						left: {
							bsonType: "double"
						},
						right: {
							bsonType: "double"
						}
					}
				},
				glass_color:{
					bsonType: "object",
					properties: {
						left: {
							bsonType: "string"
						},
						right: {
							bsonType: "string"
						}
					}
				},
				frame: {
					bsonType: "object",
					required: ["color", "type"],
					additionalProperties: false,
					properties: {
						color: {
							bsonType: "string"
						},
						type: {
							enum: ["metal","paste","float"]
						}
					}
				},
				price: {
					bsonType: "double"
				},
				client_id: {
					bsonType: "string"
				},
				vendor: {
					bsonType: "string"
				}
			}
		}
	}
});

db.createCollection("clients",{
	validator:{
		$jsonSchema: {
			bsonType: "object",
			required:["_id", "name", "phone"],
			properties:{
				_id: {
					bsonType: "string"
				},
				name: {
					bsonType: "string"
				},
				phone: {
					bsonType: "string",
					maxLength: 9,
					minLength: 9
				},
				mail: {
					bsonType: "string",
					$regex: /@\.com$/
				},
				address: {
					bsonType: "object",
					required: ["cp"],
					properties: {
						street: {
							bsonType: "string"
						},
						number: {
							bsonType: "string"
						},
						floor: {
							bsonType: "string"
						},
						door: {
							bsonType: "string"
						},
						city: {
							bsonType: "string"
						},
						cp: {
							bsonType: "string",
							maxLength: 5,
							minLength: 5
						},
						country: {
							bsonType: "string"
						}
					}
				},
				reg_date: {
					bsonType: "$date"
				},
				recommendedBy: {
					bsonType: "string"
				}
			}
		}		
	}		
});   

//se crean proveedores (documentos de la colección suppliers)
print("*************creating suppliers*************");
db.suppliers.insertMany([
	{"_id":"P-001", "name":"UnProveedor", "nif": "11111111X", "phone":"931111111", "fax":"931111111", "address": {"cp": "08001", "city": "Barcelona"}, "brands":["Nisu","Nologo"]},
	{"_id":"P-002", "name":"OtroProveedor", "nif": "22222222Y", "phone":"932222222", "fax":"932222222", "address": {"cp": "08002", "city": "Barcelona"}, "brands":["Adudas","Conversa"]},
	{"_id":"P-003", "name":"TercerProveedor", "nif": "33333333Z", "phone":"913333333", "fax":"913333333", "address": {"street":"unaCalle","cp": "28003", "city": "Madrid"}, "brands":["Daigual"]},
]);

print("*************creating glasses*************");
db.glasses.insertMany([	
	{"_id":"G-001", "brand":"Nisu", "graduation":{"left":1.25, "right":1.00}, "glass_color":{"left":"none","right":"none"},
	"frame":{"color":"black","type":"metal"}, "price": 100, "client_id":"C-001", "vendor": "UnVendedor"},
	{"_id":"G-002", "brand":"Adudas", "graduation":{"left":0.25, "right":0.5}, "glass_color":{"left":"none","right":"none"},
	"frame":{"color":"red","type":"float"}, "price": 95, "client_id":"C-003", "vendor": "otroVendedor"},
	{"_id":"G-003", "brand":"Conversa","graduation":{"left":0.25, "right":0.5}, "glass_color":{"left":"none","right":"none"},
	"frame":{"color":"blue","type":"paste"}, "price": 98},
	{"_id":"G-004", "brand":"Daigual","graduation":{"left":2.25, "right":2.0}, "frame":{"color":"black","type":"float"}, 
	"price": 108, "client_id":"C-002", "vendor":"UnVendedor"},
	{"_id":"G-005", "brand":"Nologo","graduation":{"left":2.25, "right":2.0}, "frame":{"color":"black","type":"float"}, 
	"price": 118, "client_id":"C-002", "vendor":"alguien"}
]);

print("*************creating clients*************");
db.clients.insertMany([	
	{"_id":"C-001","name":"UnCliente","phone":"666666666","mail":"mail@cliente.com", "address":{"street":"calle","number":"5","floor":"3","cp":"08001"}, "reg_date":"2017-05-01"},
	{"_id":"C-002","name":"OtroCliente","phone":"777777777","mail":"mail@otrocliente.com", "address":{"street":"alguna","number":"2","floor":"1","door": "1", "cp":"08011", "city":"Barcelona"},"reg_date":"2017-05-15", "recommendedBy":"C-001"},
	{"_id":"C-003","name":"Alguno","phone":"888888888","mail":"mail@alguncliente.com", "address":{"street":"nosesabe","number":"22","floor":"1","cp":"08021"},"reg_date":"2017-05-21", "recommendedBy":"C-001"}
]);

print("SCRIPT FINISHED");
  

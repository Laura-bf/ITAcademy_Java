/*
*Script de creación y carga de la base de datos
*/

print("STARTING SCRIPT");

//Host de la base de datos
conn = new Mongo("localhost");

//Nombre de la base de datos que se utilizará
db = conn.getDB("pizzeria");

//se limpia la bd por si hay datos persistentes
db.dropDatabase();

//se crean las colecciones del modelo de datos 
db.createCollection("clientes", {
	validator:{
		$jsonSchema: {
			bsonType: "object",
			required: ["_id","nombre","apellidos","telefono","direccion","cp","provincia","localidad"],
			properties:{
				_id: {bsonType: "string"},
				nombre: {bsonType: "string"},
				apellidos: {bsonType: "string"},
				telefono: {bsonType: "string"},
				direccion: {bsonType: "string"},
				cp: {bsonType: "string"},
				provincia:{
					bsonType: "object",
					required: ["_id","nombre"],
					additionalProperties: false,
					properties: {
						_id: {bsonType: "string"},
						nombre: {bsonType: "string"}
					}
				},
				localidad:{
					bsonType: "object",
					required: ["_id","nombre"],
					additionalProperties: false,
					properties: {
						_id: {bsonType: "string"},
						nombre: {bsonType: "string"}
					}
				},
				pedidos: {
					bsonType: "array",
					uniqueItems: true,
					items: {bsonType: "string"}
				}
			}
		}
	}
});

db.createCollection("productos", {
	validator: {
		$jsonSchema: {
			bsonType: "object",
			required: ["_id", "nombre", "tipo", "precio"],
			properties: {
				_id: {bsonType: "string"},
				nombre: {bsonType: "string"},
				tipo: {
					enum: ["pizza", "hamburguesa", "bebida"]
				},
				descripcion: {bsonType: "string"},
			
				categoria: {
					bsonType: "object",
					required: ["_id","nombre"],
					additionalProperties: false,
					properties: {
						_id: {bsonType: "string"},
						nombre: {bsonType: "string"},
					}
				},
				precio: {bsonType: "double"}
			}
		}
	}
});

db.createCollection("pedidos", {
	validator: {
		$jsonSchema: {
			bsonType: "object",
			required: ["_id","fechaHora","entrega","productos","precioTotal","tienda"],
			properties: {
				_id: {bsonType: "string"},
				fechaHora: {bsonType: "date"},
				entrega: {
					enum: ["domicilio", "tienda"]
				},
				productos: {
					bsonType: "array",
					items: {bsonType: "string"}
				},
				precioTotal: {bsonType: "double"},
				tienda: {bsonType: "string"},
				entregaDomicilio: {
					bsonType: "object",
					required: ["repartidor", "horaEntrega"],
					properties: {
						repartidor: {bsonType: "string"},
						horaEntrega: {bsonType: "string"}
					}
				}
			}
		}
	}
});

db.createCollection("tiendas", {
	validator: {
		$jsonSchema: {
			bsonType: "object",
		    required: ["_id","direccion","cp","provincia","localidad"],
   			properties: {
    			_id: {
        			bsonType: "string"
      			},
      			direccion: {
        			bsonType: "string"
      			},
      			cp: {
        			bsonType: "string",
        			maxLength: 5,
        			minLength: 5
      			},
      			provincia: {
        			bsonType: "object",
        			required: ["_id","nombre"],
        			additionalProperties: false,
        			properties: {
          				_id: {
            				bsonType: "string"
          				},
          				nombre: {
            				bsonType: "string"
          				}
        			}
      			},
      			localidad: {
        			bsonType: "object",
       		 		required: ["_id","nombre"],
        			additionalProperties: false,
        			properties: {
         				_id: {
            				bsonType: "string"
          				},
          				nombre: {
            				bsonType: "string"
          				}
        			}
      			},
      			empleados: {
        			bsonType: "array",
        			uniqueItems: true,
        			items: {
          				bsonType: "object",
          				required: ["_id","nombre","apellidos","telefono","categoria"],
          				properties: {
           		 			_id: {
              					bsonType: "string"
            				},
            				nombre: {
              					bsonType: "string"
            				},
            				apellidos: {
              					bsonType: "string"
            				},
            				telefono: {
              					bsonType: "string",
              					maxLength: 9,
              					minLength: 9
            				},
            				categoria: {
              					enum: ["cocinero","repartidor"]
            				}
          				}
        			}
      			}
    		}
  		}
	}
});

db.createCollection("provincias", {
	validator: {
		$jsonSchema: {
			bsonType: "object",
			required: ["_id", "nombre"],
			properties: {
				_id: {bsonType: "string"},
				nombre: {bsonType: "string"},
				localidades: {
					bsonType: "array",
					uniqueItems: true,
					items: {
						bsonType: "object",
						required: ["_id", "nombre"],
						properties: {
							_id: {bsonType: "string"},
							nombre: {bsonType: "string"},
						}
					}
				}
			}
		}
	}
});
//se crean provincias (documentos de la colección provincias)
print("*************creating provincias*************");
db.provincias.insertMany([
	{"_id":"p-01","nombre":"Barcelona","localidades":[{"_id":"l-01","nombre":"Barcelona"},{"_id":"l-02","nombre":"Badalona"}]},
	{"_id":"p-02","nombre":"Madrid","localidades":[{"_id":"l-03","nombre":"Madrid"}]},
	{"_id":"p-03","nombre":"Almeria","localidades":[{"_id":"l-04","nombre":"Almería"}]}
])

//se crean clientes (documentos de la colección clientes)
print("*************creating clientes*************");
db.clientes.insertMany([
	{"_id":"C-001","nombre":"nombreCliente","apellidos":"apellidosCliente","telefono":"666666666","direccion":"C/Calle,1,1,1","cp":"08001",
	"provincia":{"_id":"p-01","nombre":"Barcelona"},"localidad":{"_id":"l-01","nombre":"Barcelona"},"pedidos":["001","005"]},
	{"_id":"C-002","nombre":"otroCliente","apellidos":"apellidosOtroCliente","telefono":"777777777","direccion":"C/OtraCalle,22,2ª,2ª","cp":"28001",
	"provincia":{"_id":"p-02","nombre":"Madrid"},"localidad":{"_id":"l-02","nombre":"Madrid"},"pedidos":["P-002","004"]},
	{"_id":"C-003","nombre":"ultimoCliente","apellidos":"apellidosUltimoCliente","telefono":"888888888","direccion":"C/Yunamas,5","cp":"04001",
	"provincia":{"_id":"p-03","nombre":"Almería"},"localidad":{"_id":"l-03","nombre":"Almería"},"pedidos":["003"]},
]);

//se crean productos (documentos de la colección productos)
print("*************creating productos*************");
db.productos.insertMany([
	{"_id":"P-001","nombre":"Carbonara","tipo":"pizza", "descripcion":"aqui los ingredientes","foto":"/S2.03_MongoDB/N1-Pizzeria/images/pizza.png","categoria":{"_id":"pizCat001","nombre":"clásicas"},"precio":7.5},
	{"_id":"P-002","nombre":"Margarita","tipo":"pizza", "descripcion":"aqui los ingredientes","foto":"/S2.03_MongoDB/N1-Pizzeria/images/pizza.png","categoria":{"_id":"pizCat001","nombre":"clásicas"},"precio":6.5},
	{"_id":"P-003","nombre":"De la Casa","tipo":"pizza", "descripcion":"aqui los ingredientes","foto":"/S2.03_MongoDB/N1-Pizzeria/images/pizza.png","categoria":{"_id":"pizCat002","nombre":"especiales"},"precio":8.5},
	{"_id":"P-004","nombre":"Sencilla","tipo":"hamburguesa", "descripcion":"aqui los ingredientes","precio":3.5},
	{"_id":"P-005","nombre":"Con Queso","tipo":"hamburguesa", "descripcion":"aqui los ingredientes","precio":4.5},
	{"_id":"P-006","nombre":"Cerveza","tipo":"bebida", "precio":2.0},
	{"_id":"P-007","nombre":"Agua","tipo":"bebida", "precio":1.5},
]);

//se crean pedidos (documentos de la colección pedidos)
print("*************creating pedidos*************");
db.pedidos.insertMany([
	{"_id":"001","fechaHora":new Date(),"entrega":"domicilio","productos":["P-001","P-005","P-007","P-006"], "precioTotal":15.50, "tienda":"T-001", 
	"entregaDomicilio": {"repartidor":"E-001","horaEntrega":"20:23"}},
	{"_id":"002","fechaHora":new Date(),"entrega":"tienda","productos":["P-002","P-006"], "precioTotal":8.5, "tienda":"T-002"},
	{"_id":"003","fechaHora":new Date(),"entrega":"tienda","productos":["P-004","P-005","P-006","P-006"], "precioTotal":12, "tienda":"T-001"},
	{"_id":"004","fechaHora":new Date(),"entrega":"domicilio","productos":["P-001","P-002","P-003","P-006", "P-006","P-006"], "precioTotal":28.50, "tienda":"T-002", 
	"entregaDomicilio": {"repartidor":"E-004","horaEntrega":"21:03"}},
	{"_id":"005","fechaHora":new Date(),"entrega":"tienda","productos":["P-005","P-006"], "precioTotal":5.5, "tienda":"T-002"},
]);

//se crean tiendas (documentos de la colección tiendas)
print("*************creating productos*************");
db.tiendas.insertMany([
	{"_id":"T-001", "direccion":"C/direccionTienda,1","cp":"08001","provincia":{"_id":"p-01","nombre":"Barcelona"},"localidad":{"_id":"l-01","nombre":"Barcelona"},
	"empleados":[{"_id":"E-001", "nombre":"nombreEmpleado", "apellidos":"apellidosEmpleado", "telefono":"666666666", "categoria":"repartidor"},
				 {"_id":"E-002", "nombre":"otroEmpleado", "apellidos":"apellidosOtro", "telefono":"777777777", "categoria":"cocinero"}]},
	{"_id":"T-002", "direccion":"C/direccionOtraTienda,1","cp":"28001","provincia":{"_id":"p-02","nombre":"Madrid"},"localidad":{"_id":"l-02","nombre":"Madrid"},
	"empleados":[{"_id":"E-003", "nombre":"nombEmpl", "apellidos":"apellEmpl", "telefono":"888888888", "categoria":"cocinero"},
				 {"_id":"E-004", "nombre":"otroEmpl", "apellidos":"apelOtro", "telefono":"999999999", "categoria":"repartidor"}]},				 
]);

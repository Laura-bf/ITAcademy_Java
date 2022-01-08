/*
*Script de creación de vistas que relacionan las distintas colecciones
*/

print("STARTING SCRIPT");

//Host de la base de datos
conn = new Mongo("localhost");

//Nombre de la base de datos que se utilizará
db = conn.getDB("optica");
 
 db.createView("clientsWithInfoGlasses",
 				"clients",
				[{'$lookup': {
      						'from': 'glasses', 
      						'localField': '_id', 
      						'foreignField': 'client_id', 
					   		'as': 'glasses'
   					}
  				}]
);
				
db.createView("glassesWithInfoSuppliers",
 				"glasses",
				[{'$lookup': {
      						'from': 'suppliers', 
      						'localField': 'brand', 
      						'foreignField': 'brands', 
					   		'as': 'supplier'
   					}
  				}]
);		

db.createView("suppliersWithInfoGlasses",
 				"suppliers",
				[{'$lookup': {
      						'from': 'glasses', 
      						'localField': 'brands', 
      						'foreignField': 'brand', 
					   		'as': 'glasses'
   					}
  				}]
);			
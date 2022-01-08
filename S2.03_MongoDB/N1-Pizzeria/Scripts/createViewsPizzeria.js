/*
*Script de creación de vistas que relacionan las distintas colecciones
*/

print("STARTING SCRIPT");

//Host de la base de datos
conn = new Mongo("localhost");

//Nombre de la base de datos que se utilizará
db = conn.getDB("pizzeria");
 
 db.createView("tickets",
 				"pedidos",
				[{'$lookup': {
      						'from': 'productos', 
      						'localField': 'productos', 
      						'foreignField': '_id', 
					   		'as': 'productos',
   					}
   					}
  				]
);

db.createView("ventasTiendas",
 				"tiendas",
				[{'$lookup': {
      						'from': 'pedidos',
      						'localField': '_id', 
      						'foreignField': 'tienda', 
					   		'as': 'ventas',
   					}
   					},
   					{$project:{"ventas.tienda":0}}
  				]
);

//esto lo he hecho para probarlo, no he sido capaz de usar este pipeline para crear una vista que calculara el precio total. Por eso he tenido que poner el precio calculado a la hora de crear pedidos.				
db.pedidos.aggregate([
	{'$lookup': {
     	'from': 'productos', 
     	'localField': 'productos', 
     	'foreignField': '_id', 
		'as': 'productos',
   		}
   	},
	{$unwind:"$productos"},
	{$group:{_id:"$_id",totales:{$sum:"$productos.precio"}}},
	{$out:"importesTickets"}
]);


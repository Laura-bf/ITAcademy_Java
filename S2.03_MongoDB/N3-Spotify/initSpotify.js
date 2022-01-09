/*
*Script de creación y carga de la base de datos
*/

print("STARTING SCRIPT");

//Host de la base de datos
conn = new Mongo("localhost");

//Nombre de la base de datos que se utilizará
db = conn.getDB("spotify");

//se limpia la bd por si hay datos persistentes
db.dropDatabase();

//******** CONTADORES DE _IDs ***********************

//se crea una collection para los contadores de los ids de cada collection
db.createCollection("counters");

//si se necesita un contador que no aún no se ha creado 
function addCounter(counterName){
	if(db.counters.find({"_id":counterName}).count()==0){
		db.counters.insert({
			"_id":counterName,
			"counter_value":0
		});
	}
};

//para obtener el siguiente id que corresponda
function getNextCounterValue(counterName){
	addCounter(counterName);
	var counterDoc = db.counters.findAndModify({
		query: {_id: counterName},
		update: {$inc:{counter_value:1}},
		new: true
	});
	return counterDoc.counter_value;
};

//para obtener el último id utilizado
function getLastCounterValue(counterName){
	var doc = db.counters.find({
		query: {_id: counterName},
		new: true
	});
	return doc.counter_value;
};

//******************** USERS *****************************

//se crea la collection users y se añade un usuario free, otro premium-paypal, y otro premium-tarjeta
//las subscripciones, los métodos de pago y los pagos son documentos anidados con sus propios ids
//por ahora no se añaden ni playlists ni favoritos, sería sólo el registro de los usuarios
db.users.insertMany([
	{
	"user_id": getNextCounterValue("user_id"),
	"email": "email@usuario.com","password": "012345678","name": "nombreUsuario","birthDate": new Date("1981-05-20"),"sex": "M","country": "España","cp": "08001", 
 	"subscriber":"free","playlists":[{"list_id":1},{"list_id":3}],
 	"favourites":[{"song_id":12},{"album_id": 2}, {"song_id":10}], "follows":[{"artist_id":1},{"artist_id":3}]
 	},
 	{
 	"user_id": getNextCounterValue("user_id"),
 	"email": "email@otroUsuario.com","password": "00000000","name": "otroUsuario", "birthDate": new Date("1991-12-20"),"sex": "M","country": "España","cp": "08035", 
 	"subscriber":"premium",
 	"subscription":{
 		"subs_id": getNextCounterValue("subs_id"),
 		"regDate": new Date(), "renewDate": new Date("2023-01-09"), 
 		"payment_mode":{"paypal_id": getNextCounterValue("paypal_id"), "paypal":"nombreDeUsuarioEnPayPal"}, 
 		"payments":{"order_id":getNextCounterValue("order_id"), "date": new Date(), "amount": 20.00}
 		},
 	"playlists":[{"list_id":2}],"favourites":[{"album_id": 1},{"song_id":1},{"album_id": 2}, {"song_id":10}], "follows":[{"artist_id":1}],
 	},
 	{
 	"user_id": getNextCounterValue("user_id"),
 	"email": "email@tercerUsuario.com","password": "11111111","name": "tercerUsuario", "birthDate": new Date("1987-02-05"),
 	"sex": "H","country": "España","cp": "08004", "subscriber":"premium",
 	"subscription":{
 		"subs_id": getNextCounterValue("subs_id"),
 		"regDate": new Date(), "renewDate": new Date("2023-01-09"), 
 		"payment_mode":{"cc_id": getNextCounterValue("cc_id"), "IBAN":"ES000000000000000000000", "month":"01", "year":"2020", "cv":"000"}, 
 		"payments":{"order_id":getNextCounterValue("order_id"), "date": new Date(), "amount": 20.00}
 		},"playlists":[],"favourites":[], "follows":[{"artist_id":1},{"artist_id":2},{"artist_id":3}]
 	},
 ]);

//***************** PLAYLISTS ***********************************

//funciones para rellenar automáticamente campos de las playlists:
function getTotalSongs(listId){
	var playlistDoc = db.playlists.aggregate([
		{$match:{list_id:1}},
		{$project:{total: {$cond: {if: {"$isArray": "$songs"},
      						then: {$size: "$songs"},
      						else: "empty"}}}
		}
	]);
	return playlistDoc.total;
};

//se crea la collection playlists y se añaden tres: una activa, otra activa-compartida y otra borrada
//las playlists las crean usuarios que ya están registrados así que para crearlas se usarán los user_id que ya hemos creado 
db.playlists.insertMany([
	{
	"list_id": getNextCounterValue("list_id"),"user_id":1, "title": "playlistSoloActiva", "created":new Date(), 
	"status": "active", "shared": false, 
	"songs":[{"song_id":1},{"song_id":11}, {"song_id":6}], 
//no funciona porque el id que se le pasa no tiene ningún valor, no funciona lo de lastCounterValue?????	
	"totalSongs":getTotalSongs(getLastCounterValue("list_id"))
	},
	{
	"list_id": getNextCounterValue("list_id"),"user_id":2, "title": "playlistCompartida", "created":new Date(), 
	"status": "active", "shared": true, 
	"songs":[{"song_id":7, "addedBy": 2, "date": new Date()},{"song_id":13, "addedBy": 3, "date": new Date()}, {"song_id":2, "addedBy": 1, "date": new Date()}], 
//no funciona porque el id que se le pasa no tiene ningún valor, no funciona lo de lastCounterValue?????	
	"totalSongs":getTotalSongs(getLastCounterValue("list_id"))
	},
	{
	"list_id": getNextCounterValue("list_id"),"user_id":1, "title": "playlistEliminada", "created":new Date(), 
	"status": "deleted",
	"songs":[{"song_id":9, "addedBy": 1, "date": new Date()},{"song_id":10, "addedBy": 2, "date": new Date()}, {"song_id":3, "addedBy": 3, "date": new Date()}], 
//lo de getId es una prueba resulta que no funciona lo de conseguir el último id, sale como undefined en vez de un valor, por eso luego no fuciona lo del total de songs
	"PRUEBAgetId":getLastCounterValue("list_id")

	}
]);

//***************** ALBUMS ***********************************

//se crea la collection albums y se añaden dos
//las canciones son documentos anidados de los albumes
db.albums.insertMany([
	{
	"album_id": getNextCounterValue("album_id"), "title": "tituloAlbum", "year":"1970", "image":"/path_img_portada.png", "artist":1,
	"songs":[
		{"song_id": getNextCounterValue("song_id"), "title":"tituloCancion", "duration": 2, "reproducciones" :22},
		{"song_id": getNextCounterValue("song_id"), "title":"otraCancion", "duration": 3, "reproducciones" :10},
		{"song_id": getNextCounterValue("song_id"), "title":"unaCancion", "duration": 4, "reproducciones" :15},
		{"song_id": getNextCounterValue("song_id"), "title":"songssongssongs", "duration": 5, "reproducciones" :8}]
	},
	{
	"album_id": getNextCounterValue("album_id"), "title": "otroAlbum", "year":"1981", "image":"/path_img_portada.png", "artist":3,
	"songs":[
		{"song_id": getNextCounterValue("song_id"), "title":"otraCancion", "duration": 2, "reproducciones" :22},
		{"song_id": getNextCounterValue("song_id"), "title":"tituloCancion", "duration": 3, "reproducciones" :10},
		{"song_id": getNextCounterValue("song_id"), "title":"unaCancion", "duration": 4, "reproducciones" :15},
		{"song_id": getNextCounterValue("song_id"), "title":"aSong", "duration": 5, "reproducciones" :8},
		{"song_id": getNextCounterValue("song_id"), "title":"otraCancion", "duration": 2, "reproducciones" :22},
		{"song_id": getNextCounterValue("song_id"), "title":"tituloCancion", "duration": 3, "reproducciones" :10},
		{"song_id": getNextCounterValue("song_id"), "title":"unaCancion", "duration": 4, "reproducciones" :15},
		{"song_id": getNextCounterValue("song_id"), "title":"aSong", "duration": 5, "reproducciones" :8}]
	}
]);

//***************** ARTISTS ***********************************

//se crea la collection de artistas y se añaden tres
db.artists.insertMany([
	{"artist_id": getNextCounterValue("artist_id"), "name":"nombreDelArtista", "photo": "/path_img_artista.png", "relatedArtists":[{"artist_id":2}], "albums":[{"album_id":1}]},
	{"artist_id": getNextCounterValue("artist_id"), "name":"otroArtista", "photo": "/path_img_artista.png", "relatedArtists":[{"artist_id":1},{"artist_id":3}], "albums":[{"album_id":3}]},
	{"artist_id": getNextCounterValue("artist_id"), "name":"artistaDeLaVida", "photo": "/path_img_artista.png", "relatedArtists":[{"artist_id":2}], "albums":[{"album_id":2}]},
]);
	


	
	
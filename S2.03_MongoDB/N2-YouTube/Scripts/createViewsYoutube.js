/*
*Script de creación de vistas que relacionan las distintas colecciones
*/

print("STARTING SCRIPT");

//Host de la base de datos
conn = new Mongo("localhost");

//Nombre de la base de datos que se utilizará
db = conn.getDB("youtube");


//crea una collection que guarda el total de likes y dislikes de cada video
db.videos.aggregate([
  {
    '$facet': {
      'videoLikes': [
        {
          '$unwind': '$likes'
        }, {
          '$group': {
            '_id': '$_id', 
            'totalLikes': {
              '$sum': 1
            }
          }
        }
      ], 
      'videoDislikes': [
        {
          '$unwind': '$likes'
        }, {
          '$group': {
            '_id': '$_id', 
            'totalDislikes': {
              '$sum': 1
            }
          }
        }
      ]
    }
  },
  {$out:"videos_likes_dislikes"}
]);
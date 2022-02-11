package com.diceGame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import com.diceGame.model.domain.MongoPlayer;
import com.diceGame.model.persistance.MongoPlayerRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


@AutoConfigureDataMongo
@SpringBootTest
class MongoPlayerRepositoryTest {
	
	@Mock
	private MongoPlayerRepository playerRepository;
	
	private static MongoPlayer player;
	
	private static MongoClient mongoClient;
	private static MongoDatabase db;
	
	@BeforeAll
	public static void init() {
		player = new MongoPlayer("nameTest","password");
		try {
			mongoClient = MongoClients.create();
			db = mongoClient.getDatabase("test");
		}catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	@AfterAll
	public static void close() {
		try {
			db.drop();
			mongoClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test 
	final void savePlayer() {
		playerRepository.save(player);
		
		assertThat(newPlayerExistsByMongoQuery("nameTest"));
	}
	
	@Test
	final void findAllPlayers() {
		playerRepository.deleteAll();
		playerRepository.save(new MongoPlayer("test1", "pw"));
		playerRepository.save(new MongoPlayer("test2", "pw"));
		playerRepository.save(new MongoPlayer("test3", "pw"));
		
		int size =  playerRepository.findAll().size();
		System.out.println(size);
		
		assertTrue(size==3);
	}
	
	private boolean newPlayerExistsByMongoQuery(String name) {
		MongoCollection<Document> players = db.getCollection("players");
		Document doc = players.find(new Document("name",name)).first();
		if(doc != null)
			return true;
		else 
			return false;
	}

}

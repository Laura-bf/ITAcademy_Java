package com.diceGame.Nivel3;


import static org.assertj.core.api.Assertions.assertThat;

import org.bson.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import com.diceGame.nivel3.Application;
import com.diceGame.nivel3.domain.documents.Roll;
import com.diceGame.nivel3.persistance.repositories.RollRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@AutoConfigureDataMongo
@SpringBootTest(classes = {Application.class})
class RollRepositoryTest {

	@Mock
	private RollRepository rollRepository;
	
	private static Roll roll;
	
	private static MongoClient mongoClient;
	private static MongoDatabase db;
	
	@BeforeAll
	public static void init() {
		roll = new Roll();
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
	final void saveRoll() {
		rollRepository.save(roll);
		
		assertThat(newRollExistsByMongoQuery());
	}

	private boolean newRollExistsByMongoQuery() {
		MongoCollection<Document> players = db.getCollection("rolls");
		Document doc = players.find().first();
		if(doc != null)
			return true;
		else 
			return false;
	}
}

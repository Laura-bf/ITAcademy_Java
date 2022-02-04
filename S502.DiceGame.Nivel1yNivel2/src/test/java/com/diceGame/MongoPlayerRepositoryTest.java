package com.diceGame;

import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;


@AutoConfigureDataMongo
@SpringBootTest
class MongoPlayerRepositoryTest {

	@Test
	final void testSaveMongoPlayer() {
		fail("Not yet implemented");
	}
}

package com.diceGame.Nivel3;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.diceGame.nivel3.Application;
import com.diceGame.nivel3.domain.entities.Player;

//@DataJpaTest
@SpringBootTest(classes = {Application.class})
class PlayerTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	final void testSavePlayer() {
		Player testPlayer = new Player("testName","Aa123456!");
		Player savedPlayerData = this.entityManager.persistAndFlush(testPlayer);
		assertThat(savedPlayerData.getName().equals("testName"));
	}
}


package com.diceGame.model.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class PlayerPersistenceTest {

	@Autowired
	private TestEntityManager entityManager;
	
	private static Player testPlayer;

	@BeforeAll
	public static void setUp() {
		testPlayer = new Player("testName","pw");
	}
	
	@Test
	final void savePlayer() {
		Player savedPlayerData = this.entityManager.persistAndFlush(testPlayer);
		assertThat(savedPlayerData.getName().equals("testName"));
	}
	
	@Test
	public void createPlayerNullNameThrowsIllegalArgumentException() {
		try {
			testPlayer = new Player("","pw");
			fail("Exception Expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("El nombre es un campo obligatorio para poder registrarse"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}

	

}


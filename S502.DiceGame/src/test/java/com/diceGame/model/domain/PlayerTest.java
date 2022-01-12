package com.diceGame.model.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class PlayerTest {

	@Autowired
	private TestEntityManager entityManager;
	
	private static Player testPlayer;
	private static Roll roll1;
	private static Roll roll2;
	private static Roll roll3;
	
	@BeforeAll
	public static void setUp() {
		testPlayer = new Player("testName","pw");
		roll1 = new Roll();
		roll2 = new Roll();
		roll3 = new Roll();
	}
	
	@Test
	final void savePlayer() {
		Player savedPlayerData = this.entityManager.persistAndFlush(testPlayer);
		assertThat(savedPlayerData.getName().equals("testName"));
	}
	
	@Test
	public void createPlayerNullNameThrowsException() {
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

	@Test
	final void testCalculateRate() {
		roll1.setWon(true);;
		roll2.setWon(true);
		roll3.setWon(false);
		testPlayer.addRoll(roll1);
		testPlayer.addRoll(roll2);
		testPlayer.addRoll(roll3);
		double expected = 2/3d;
		
		testPlayer.calculateRate();
		double result = testPlayer.getRate();
		
		assertTrue(expected*100==result);
	}

}


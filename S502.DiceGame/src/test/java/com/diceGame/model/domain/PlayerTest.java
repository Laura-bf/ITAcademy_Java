package com.diceGame.model.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PlayerTest {
	
	@Test
	final void testCalculateRate() {

		Player testPlayer = new Player("testName","pw");;
		Roll roll1 = new Roll();
		Roll roll2 = new Roll();
		Roll roll3 = new Roll();
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

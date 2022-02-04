package com.diceGame.Nivel3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.diceGame.nivel3.Application;
import com.diceGame.nivel3.domain.documents.Roll;

@SpringBootTest(classes = {Application.class})
class RollTest {

	@Test
	void testPlayRoll_RandomValuesBetween1And6() {
		Roll roll = new Roll();
		roll.playRoll();
		
		assertTrue(roll.getValueDice1()<=6);
		assertTrue(roll.getValueDice1()>=1);
		assertTrue(roll.getValueDice2()<=6);
		assertTrue(roll.getValueDice2()>=1);
	}
	
	@Test
	void testPlayRoll_WinWhenSumEquals7() {
		Roll roll = new Roll();
		roll.playRoll();
		int result = roll.getValueDice1()+roll.getValueDice2();
		if(result==7)
			assertTrue(roll.isWon());
		else
			assertTrue(!roll.isWon());
	}
}
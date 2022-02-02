package com.diceGame;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.diceGame.model.domain.Player;
import com.diceGame.model.domain.Roll;

@DataJpaTest
@ActiveProfiles("test")
class PlayerTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	final void testSavePlayer() {
		Player testPlayer = new Player("testName","Aa123456!");
		Player savedPlayerData = this.entityManager.persistAndFlush(testPlayer);
		assertThat(savedPlayerData.getName().equals("testName"));
	}
	
	@Test
	public void testAddRoll() {
		Player testPlayer = new Player("testName","Aa123456!");
		
		testPlayer.addRoll();
		Player savedPlayerData = this.entityManager.persistAndFlush(testPlayer);
		
		assertTrue(savedPlayerData.getRollList().size()>0);
	}
	
	@Test
	final void testCalculateRate() {
		Player testPlayer = new Player("testName","pw");
		List<Roll> rolllist = testPlayer.getRollList();
		Roll roll1 = new Roll();
		Roll roll2 = new Roll();
		Roll roll3 = new Roll();
		roll1.setWon(true);;
		roll2.setWon(true);
		roll3.setWon(false);
		rolllist.add(roll1);
		rolllist.add(roll2);
		rolllist.add(roll3);
		double expected = 2/3d;
		
		testPlayer.calculateRate();
		double result = testPlayer.getRate();
		
		assertTrue(expected*100==result);
	}
}


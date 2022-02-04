package com.diceGame.Nivel3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.diceGame.nivel3.Application;
import com.diceGame.nivel3.domain.models.PlayerDTO;
import com.diceGame.nivel3.domain.models.RollDTO;

@SpringBootTest(classes = {Application.class})
class PlayerDTOTest {

	@Test
	final void testCalculateRate() {
		PlayerDTO testPlayer = new PlayerDTO("testName","pw");
		List<RollDTO> rolllist = testPlayer.getRollList();
		RollDTO roll1 = new RollDTO();
		RollDTO roll2 = new RollDTO();
		RollDTO roll3 = new RollDTO();
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

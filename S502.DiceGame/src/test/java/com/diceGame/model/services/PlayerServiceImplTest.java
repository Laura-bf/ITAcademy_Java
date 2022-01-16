package com.diceGame.model.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.diceGame.model.domain.Player;
import com.diceGame.model.domain.Roll;
import com.diceGame.model.repositories.PlayerRepository;

@SpringBootTest
@ActiveProfiles("test")
@Transactional //para que no de error de failed to laizily inititalize (The problem is caused by accessing an attribute with the hibernate session closed)
class PlayerServiceImplTest {

	@Autowired
	PlayerService playerService;
	
	@Autowired
	PlayerRepository playerRepository;
	
//	@Test
//	final void checkNameThatExists_returnTRUE() {
//		playerRepository.save(new Player("test", "Aa123456!"));
//		assertTrue(playerService.checkNameExists("test"));
//	}
//	@Test
//	final void checkNameThatNOTExists_returnFALSE() {
//		playerRepository.save(new Player("test", "Aa123456!"));
//		assertFalse(playerService.checkNameExists("noName"));
//	}
//	@Test
//	final void checkEMPTYName_returnIllegalArgumentException() {
//		playerRepository.save(new Player("test", "Aa123456!"));
//		try {
//			playerService.checkNameExists("");
//			fail("Exception Expected!");
//		} catch(IllegalArgumentException e) {
//			assertThat(IllegalArgumentException.class.equals(e.getClass()));
//			assertThat(e.getMessage().equals("Es necesario indicar un nombre"));
//		}catch(Exception e) {
//			fail("wrong exception thrown");
//		}
//	}

	@Test
	final void addPlayer() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		int totalPlayers = 2;
		
		Player added = new Player("added","Aa123456!");
		playerService.addPlayer(added);
		long result = playerRepository.count();
		
		assertTrue(result==totalPlayers+1);
	}
	@Test
	final void addRegisteredPlayer_NameOK_PwOK() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		int totalPlayers = 2;

		playerService.addRegisteredPlayer("added","Aa123456!");
		long result = playerRepository.count();
		
		assertTrue(result==totalPlayers+1);
	}
	@Test
	final void addRegisteredPlayer_NameExists_throwsIllegalArgumentException() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		
		try {
			playerService.addRegisteredPlayer("test","Aa!000000");
			fail("Exception expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("Este nombre ya está registrado"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}
	@Test
	final void addRegisteredPlayer_EmptyName_IllegalArgumentException() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		
		try {
			playerService.addRegisteredPlayer("","Aa123456!");
			fail("Exception expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("Es necesario indicar un nombre"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}
	@Test
	final void addRegisteredPlayer_WrongFormatPW_throwsIllegalArgumentException() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		
		try {
			playerService.addRegisteredPlayer("test3","Aa3456!");//<8char
			playerService.addRegisteredPlayer("test4","Aa123456789123456!");//>15char
			playerService.addRegisteredPlayer("test5","Aaaaaaaa!");//No digit
			playerService.addRegisteredPlayer("test6","aa123456!");//No capital letter
			playerService.addRegisteredPlayer("test7","AA123456!");//No lowercase letter
			playerService.addRegisteredPlayer("test8","Aa123456");//No special char
			fail("Exception expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("Contraseña requiere:\n-Entre 8 y 15 caracteres con al menos un dígito,una mayúscula,una minúscula y un caracter especial.No admite espacios en blanco"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}

	@Test
	final void getPlayerByIdThatExists_returnPlayer() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		
		Player test = new Player("test3","Aa123456!");
		playerRepository.save(test);
		Integer id = test.getPlayerId();
		
		assertEquals(test, playerService.getPlayerById(id));
	}
	@Test
	final void getPlayerByIdThatNOTExists_returnNoSuchElementException() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		try {
			playerService.getPlayerById(3);
			fail("Exception Expected!");
		} catch(NoSuchElementException e) {
			assertThat(NoSuchElementException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("No existe ningún jugador con este id"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}
	@Test
	final void getPlayerByNameThatExists_returnPlayer() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		Player test = new Player("test3","Aa123456!");
		playerRepository.save(test);
		String name = "test3";
	
		assertEquals(test, playerService.getPlayerByName(name));
	}
	@Test
	final void getPlayerByNameThatNOTExists_returnNoSuchElementException() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		try {
			playerService.getPlayerByName("noName");
			fail("Exception Expected!");
		} catch(NoSuchElementException e) {
			assertThat(NoSuchElementException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("No existe ningún jugador con este nombre"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}
	@Test
	final void getPlayerByEMPTYName_returnIllegalArgumentException() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		try {
			playerService.getPlayerByName("");
			fail("Exception Expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("Es necesario indicar un nombre"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}
	@Test
	final void getAllPlayers() {
		Player test1 = new Player("test1", "Aa123456!");
		Player test2 = new Player("test2", "Aa123456!");
		playerRepository.save(test1);
		playerRepository.save(test2);
		List<Player> expectedList = new ArrayList<Player>();
		expectedList.add(test1);
		expectedList.add(test2);
		
		assertEquals(expectedList, playerService.getAllPlayers());
	}
	
	@Test
	final void setAnonymousPlayer() {
		Player test1 = new Player("test1", "Aa123456!");
		playerRepository.save(test1);
		Integer id = test1.getPlayerId();
		String oldName = "test1";
		
		playerService.setAnonymousPlayer(id);
		String newName = playerRepository.findById(id).get().getName();
		
		assertFalse(oldName.equals(newName));
		assertTrue(newName.equals("Anonymous"));
	}
	
	@Test
	final void addRoll() {
		Player test1 = new Player("test1", "Aa123456!");
		Player test2 = new Player("test2", "Aa123456!");
		playerRepository.save(test1);
		playerRepository.save(test2);
		Player test = new Player("test","Aa123456!");
		playerRepository.save(test);
		Integer id = test.getPlayerId();
		
		Roll roll = new Roll();
		roll.playRoll();
		
		playerService.addRoll(id, roll);
		int size = playerRepository.findById(id).get().getRollList().size();
		
		assertTrue(size>0);
	}
	
	@Test
	final void getAllRolls() {
		Player test1 = new Player("test1", "Aa123456!");
		Roll roll1 = new Roll();
		Roll roll2 = new Roll();
		roll1.playRoll();
		roll1.setWon(true);
		roll2.playRoll();
		roll2.setWon(true);
		test1.addRoll(roll1);
		test1.addRoll(roll2);
		playerRepository.save(test1);
		Player test2 = new Player("test2", "Aa123456!");
		playerRepository.save(test2);
		
		Integer id = test1.getPlayerId();
		List<Roll> expected = test1.getRollList();
		
		assertEquals(expected, playerService.getAllRolls(id));
	}
	
	@Test
	final void deleteAllRolls() {
		Player test1 = new Player("test1", "Aa123456!");
		Roll roll1 = new Roll();
		Roll roll2 = new Roll();
		roll1.playRoll();
		roll1.setWon(true);
		roll2.playRoll();
		roll2.setWon(true);
		test1.addRoll(roll1);
		test1.addRoll(roll2);
		playerRepository.save(test1);
		Player test2 = new Player("test2", "Aa123456!");
		playerRepository.save(test2);
		Integer id = test1.getPlayerId();
		int initialSize = test1.getRollList().size();
		
		playerService.deleteAllRolls(id);
		int finalSize = playerRepository.getById(id).getRollList().size();
		
		assertTrue(initialSize>finalSize);
		assertTrue(finalSize==0);
	}
	@Test
	final void getLoserPlayer_OneLoser() {
		Player test1 = new Player("test1", "Aa123456!");
		Player test2 = new Player("test2", "Aa123456!");
		test1.setRate(90d);
		test2.setRate(40d);
		playerRepository.save(test1);
		playerRepository.save(test2);
		List<Player> expected = new ArrayList<Player>();
		expected.add(test2);
		
		assertEquals(expected, playerService.getLoserPlayer());
	}
	@Test
	final void getLoserPlayer_ManyLosers() {
		Player test1 = new Player("test1", "Aa123456!");
		Player test2 = new Player("test2", "Aa123456!");
		Player test3 = new Player("test3", "Aa123456!");
		test1.setRate(90d);
		test2.setRate(40d);
		test3.setRate(40d);
		playerRepository.save(test1);
		playerRepository.save(test2);
		playerRepository.save(test3);
		List<Player> expected = new ArrayList<Player>();
		expected.add(test2);
		expected.add(test3);
		
		assertEquals(expected, playerService.getLoserPlayer());
	}
	@Test
	final void getWinnerPlayer_OneWinner() {
		Player test1 = new Player("test1", "Aa123456!");
		Player test2 = new Player("test2", "Aa123456!");
		test1.setRate(90d);
		test2.setRate(40d);
		playerRepository.save(test1);
		playerRepository.save(test2);;
		List<Player> expected = new ArrayList<Player>();
		expected.add(test1);
		
		assertEquals(expected, playerService.getWinnerPlayer());
	}
	@Test
	final void getWinnerPlayer_ManyWinners() {
		Player test1 = new Player("test1", "Aa123456!");
		Player test2 = new Player("test2", "Aa123456!");
		Player test3 = new Player("test3", "Aa123456!");
		test1.setRate(90d);
		test2.setRate(90d);
		test3.setRate(40d);
		playerRepository.save(test1);
		playerRepository.save(test2);
		playerRepository.save(test3);
		List<Player> expected = new ArrayList<Player>();
		expected.add(test2);
		expected.add(test1);
		
		assertEquals(expected, playerService.getWinnerPlayer());
	}
	
	@Test
	final void getPlayersRanking() {
		Player test1 = new Player("test1", "Aa123456!");
		Roll roll1 = new Roll();
		Roll roll2 = new Roll();
		roll1.playRoll();
		roll1.setWon(true);
		roll2.playRoll();
		roll2.setWon(true);
		test1.addRoll(roll1);
		test1.addRoll(roll2);
		playerRepository.save(test1);
		
		Player test2 = new Player("test2", "Aa123456!");
		Roll roll3 = new Roll();
		Roll roll4 = new Roll();
		roll3.playRoll();
		roll3.setWon(true);
		roll4.playRoll();
		roll4.setWon(false);
		test2.addRoll(roll3);
		test2.addRoll(roll4);
		playerRepository.save(test2);
		
		double rankingExpected = 75d;
		
		double result = playerService.getPlayersRanking();
		
		assertEquals(rankingExpected, result);
	}
	@Test
	final void getAllPlayersSortedByRate() {
		Player test1 = new Player("test1", "Aa123456!");
		Player test2 = new Player("test2", "Aa123456!");
		Player test3 = new Player("test3", "Aa123456!");
		test1.setRate(10d);
		test2.setRate(90d);
		test3.setRate(40d);
		playerRepository.save(test1);
		playerRepository.save(test2);
		playerRepository.save(test3);
		List<Player> expected = new ArrayList<Player>();
		expected.add(test2);
		expected.add(test3);
		expected.add(test1);
		
		assertEquals(expected, playerService.getAllPlayersSortedByRate());
	}
}

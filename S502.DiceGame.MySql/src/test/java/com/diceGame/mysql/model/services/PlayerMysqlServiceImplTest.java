package com.diceGame.mysql.model.services;

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

import com.diceGame.mysql.model.DTO.PlayerDTO;
import com.diceGame.mysql.model.domain.Player;
import com.diceGame.mysql.model.domain.Roll;
import com.diceGame.mysql.model.persistance.PlayerMysqlRepository;

@SpringBootTest
@ActiveProfiles("test")
@Transactional //para que no de error de failed to laizily inititalize (The problem is caused by accessing an attribute with the hibernate session closed)
class PlayerMysqlServiceImplTest {

	@Autowired
	PlayerMysqlServiceImpl playerService;
	
	@Autowired
	PlayerMysqlRepository playerRepository;
	
	@Test
	final void checkNameThatExists_returnTRUE() {
		playerRepository.save(new Player("test", "Aa123456!"));
		assertTrue(playerService.checkNameExists("test"));
	}
	@Test
	final void checkNameThatNOTExists_returnFALSE() {
		playerRepository.save(new Player("test", "Aa123456!"));
		assertFalse(playerService.checkNameExists("noName"));
	}
	@Test
	final void checkEMPTYName_returnIllegalArgumentException() {
		playerRepository.save(new Player("test", "Aa123456!"));
		try {
			playerService.checkNameExists("");
			fail("Exception Expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("Es necesario indicar un nombre"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}

	@Test
	final void addPlayer_OK() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		int totalPlayers = 2;
		
		PlayerDTO added = new PlayerDTO("added","Aa123456!");
		playerService.addPlayer(added);
		long result = playerRepository.count();
		
		assertTrue(result==totalPlayers+1);
	}
	
	@Test
	final void addPlayer_NameExists_throwsIllegalArgumentException() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		
		PlayerDTO dto = new PlayerDTO("test", "Aa123456!");
		
		try {
			playerService.addPlayer(dto);
			fail("Exception expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("Este nombre ya está registrado"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}
	@Test
	final void addPlayer_EmptyName_IllegalArgumentException() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		
		PlayerDTO dto = new PlayerDTO("", "Aa123456!");
		
		try {
			playerService.addPlayer(dto);
			fail("Exception expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("Es necesario indicar un nombre"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}
	@Test
	final void addPlayer_WrongFormatPW_throwsIllegalArgumentException() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		
		PlayerDTO dto3 = new PlayerDTO("test3","Aa3456!");//<8char
		PlayerDTO dto4 = new PlayerDTO("test4","Aa123456789123456!");//>15char
		PlayerDTO dto5 = new PlayerDTO("test5","Aaaaaaaa!");//No digit
		PlayerDTO dto6 = new PlayerDTO("test6","aa123456!");//No capital letter
		PlayerDTO dto7 = new PlayerDTO("test7","AA123456!");//No lowercase letter
		PlayerDTO dto8 = new PlayerDTO("test8","Aa123456");//No special char
		try {
			playerService.addPlayer(dto3);
			playerService.addPlayer(dto4);
			playerService.addPlayer(dto5);
			playerService.addPlayer(dto6);
			playerService.addPlayer(dto7);
			playerService.addPlayer(dto8);
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
		
		PlayerDTO dto = new PlayerDTO("test3","Aa123456!");
		dto.setPlayerId(id);
		
		assertEquals(dto, playerService.getPlayerById(id));
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
	final void getPlayerByNameThatExists_returnPlayerDTO() {
		playerRepository.save(new Player("test", "Aa123456!"));
		playerRepository.save(new Player("test2", "Aa123456!"));
		Player test = new Player("test3","Aa123456!");
		playerRepository.save(test);
		Integer id = test.getPlayerId();
		
		PlayerDTO dto = new PlayerDTO("test3","Aa123456!");
		dto.setPlayerId(id);
		String name = "test3";
	
		assertEquals(dto, playerService.getPlayerByName(name));
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
		PlayerDTO dto1 = new PlayerDTO("test1","Aa123456!");
		dto1.setPlayerId(1);
		PlayerDTO dto2 = new PlayerDTO("test2","Aa123456!");
		dto2.setPlayerId(2);
		List<PlayerDTO> expectedList = new ArrayList<PlayerDTO>();
		expectedList.add(dto1);
		expectedList.add(dto2);
		
		assertEquals(expectedList, playerService.getAllPlayers());
	}
	
	@Test
	final void setAnonymousPlayer_OK() {
		Player test1 = new Player("test1", "Aa123456!");
		playerRepository.save(test1);
		Integer id = test1.getPlayerId();
		String oldName = "test1";
		
		PlayerDTO dto = new PlayerDTO("test1", "Aa123456!");
		dto.setPlayerId(id);
		
		playerService.setAnonymousPlayer(dto);
		String newName = playerRepository.findById(id).get().getName();
		
		assertFalse(oldName.equals(newName));
		assertTrue(newName.equals("Anonymous"));
	}
	
	@Test
	final void playRoll() {
		Player test1 = new Player("test1", "Aa123456!");
		Player test2 = new Player("test2", "Aa123456!");
		playerRepository.save(test1);
		playerRepository.save(test2);
		Player test = new Player("test","Aa123456!");
		playerRepository.save(test);
		Integer id = test.getPlayerId();
		
		playerService.playRoll(id);
		int size = playerRepository.findById(id).get().getRollList().size();
		
		assertTrue(size>0);
	}
	
	@Test
	final void getAllRolls() {
		Player test1 = new Player("test1", "Aa123456!");
		List<Roll> rolllist = test1.getRollList();
		Roll roll1 = new Roll();
		Roll roll2 = new Roll();
		roll1.playRoll();
		roll1.setWon(true);
		roll2.playRoll();
		roll2.setWon(true);
		rolllist.add(roll1);
		rolllist.add(roll2);
		test1.setRollList(rolllist);
		playerRepository.save(test1);
		
		
		Integer id = test1.getPlayerId();
		List<Roll> expected = rolllist;
		
		assertEquals(expected, playerService.getAllRolls(id));
	}
	
	@Test
	final void deleteAllRolls() {
		Player test1 = new Player("test1", "Aa123456!");
		List<Roll> rolllist = test1.getRollList();
		Roll roll1 = new Roll();
		Roll roll2 = new Roll();
		roll1.playRoll();
		roll1.setWon(true);
		roll2.playRoll();
		roll2.setWon(true);
		rolllist.add(roll1);
		rolllist.add(roll2);
		test1.setRollList(rolllist);
		playerRepository.save(test1);
		
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
		PlayerDTO dto1 = new PlayerDTO("test1", "Aa123456!");
		dto1.setPlayerId(1);
		PlayerDTO dto2 = new PlayerDTO("test2", "Aa123456!");
		dto2.setPlayerId(2);
		dto1.setRate(90d);
		dto2.setRate(40d);
		List<PlayerDTO> expected = new ArrayList<PlayerDTO>();
		expected.add(dto2);
		
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
		PlayerDTO dto1 = new PlayerDTO("test1", "Aa123456!");
		dto1.setPlayerId(1);
		PlayerDTO dto2 = new PlayerDTO("test2", "Aa123456!");
		dto2.setPlayerId(2);
		PlayerDTO dto3 = new PlayerDTO("test3", "Aa123456!");
		dto3.setPlayerId(3);
		dto1.setRate(90d);
		dto2.setRate(40d);
		dto3.setRate(40d);
		List<PlayerDTO> expected = new ArrayList<PlayerDTO>();
		expected.add(dto2);
		expected.add(dto3);
		
		assertEquals(expected, playerService.getLoserPlayer());
	}
	@Test
	final void getWinnerPlayer_OneWinner() {
		Player test1 = new Player("test1", "Aa123456!");
		Player test2 = new Player("test2", "Aa123456!");
		test1.setRate(90d);
		test2.setRate(40d);
		playerRepository.save(test1);
		playerRepository.save(test2);
		PlayerDTO dto1 = new PlayerDTO("test1", "Aa123456!");
		dto1.setPlayerId(1);
		PlayerDTO dto2 = new PlayerDTO("test2", "Aa123456!");
		dto2.setPlayerId(2);
		dto1.setRate(90d);
		dto2.setRate(40d);
		List<PlayerDTO> expected = new ArrayList<PlayerDTO>();
		expected.add(dto1);
		
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
		PlayerDTO dto1 = new PlayerDTO("test1", "Aa123456!");
		dto1.setPlayerId(1);
		PlayerDTO dto2 = new PlayerDTO("test2", "Aa123456!");
		dto2.setPlayerId(2);
		PlayerDTO dto3 = new PlayerDTO("test3", "Aa123456!");
		dto3.setPlayerId(3);
		dto1.setRate(90d);
		dto2.setRate(90d);
		dto3.setRate(40d);
		List<PlayerDTO> expected = new ArrayList<PlayerDTO>();
		expected.add(dto1);
		expected.add(dto2);
		
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
		List<Roll> rolllist1 = test1.getRollList();
		rolllist1.add(roll1);
		rolllist1.add(roll2);
		test1.setRollList(rolllist1);
		playerRepository.save(test1);
		
		Player test2 = new Player("test2", "Aa123456!");
		Roll roll3 = new Roll();
		Roll roll4 = new Roll();
		roll3.playRoll();
		roll3.setWon(true);
		roll4.playRoll();
		roll4.setWon(false);
		List<Roll> rolllist2 = test2.getRollList();
		rolllist2.add(roll3);
		rolllist2.add(roll4);
		test2.setRollList(rolllist2);
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
		PlayerDTO dto1 = new PlayerDTO("test1", "Aa123456!");
		dto1.setPlayerId(1);
		PlayerDTO dto2 = new PlayerDTO("test2", "Aa123456!");
		dto2.setPlayerId(2);
		PlayerDTO dto3 = new PlayerDTO("test3", "Aa123456!");
		dto3.setPlayerId(3);
		dto1.setRate(10d);
		dto2.setRate(90d);
		dto3.setRate(40d);
		
		List<PlayerDTO> expected = new ArrayList<PlayerDTO>();
		expected.add(dto2);
		expected.add(dto3);
		expected.add(dto1);
		
		assertEquals(expected, playerService.getAllPlayersSortedByRate());
	}
}

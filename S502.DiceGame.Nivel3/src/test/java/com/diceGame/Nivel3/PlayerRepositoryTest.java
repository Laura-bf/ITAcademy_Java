package com.diceGame.Nivel3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.diceGame.nivel3.Application;
import com.diceGame.nivel3.domain.entities.Player;
import com.diceGame.nivel3.persistance.repositories.PlayerRepository;

@SpringBootTest(classes = {Application.class})
class PlayerRepositoryTest {

	@Autowired
	private PlayerRepository playerRepository;
	
	private static Player player;
	
	@BeforeAll
	public static void setUp() {
		player = new Player("nameTest","password");
	}

	@Test
	final void savePlayerAndFindById() {
		playerRepository.save(player);
		
		assertThat(playerRepository.findById(player.getPlayerId()).get().equals(player));
	}

	@Test
	final void savePlayerAndFindByName() {
		playerRepository.save(player);
		assertThat(playerRepository.findByName("nameTest").get(0).equals(player));
	}
	
	@Test
	final void findAllPlayers() {
		playerRepository.deleteAll();
		playerRepository.save(new Player("test1", "pw"));
		playerRepository.save(new Player("test2", "pw"));
		playerRepository.save(new Player("test3", "pw"));
		
		int size =  playerRepository.findAll().size();
		
		assertTrue(size==3);
	}

}

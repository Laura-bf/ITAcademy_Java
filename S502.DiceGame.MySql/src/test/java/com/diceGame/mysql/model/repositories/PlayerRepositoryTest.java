package com.diceGame.mysql.model.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.diceGame.mysql.model.domain.Player;
import com.diceGame.mysql.model.persistance.PlayerMysqlRepository;

@DataJpaTest
@ActiveProfiles("test")
class PlayerRepositoryTest {

	@Autowired
	private PlayerMysqlRepository playerRepository;
	
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
		assertThat(playerRepository.findByName("nameTest").equals(player));
	}
	
	@Test
	final void findAllPlayers() {
		playerRepository.save(new Player("test1", "pw"));
		playerRepository.save(new Player("test2", "pw"));
		playerRepository.save(new Player("test3", "pw"));
		
		int size =  playerRepository.findAll().size();
		
		assertTrue(size==3);
	}

}

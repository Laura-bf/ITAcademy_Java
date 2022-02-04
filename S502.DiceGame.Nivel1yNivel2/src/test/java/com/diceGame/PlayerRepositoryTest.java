package com.diceGame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.diceGame.model.domain.Player;
import com.diceGame.model.persistance.PlayerRepository;

@DataJpaTest
@ActiveProfiles("test")
class PlayerRepositoryTest {

	@Autowired
	private PlayerRepository playerRepository;
	
	private static Player player;
	
	private static Connection conn;
	private static Statement statement;
	
	@BeforeAll
	public static void init() {
		player = new Player("nameTest","password");
		try {
			Class.forName("org.h2.Driver"); 
			conn = DriverManager.getConnection("jdbc:h2:mem:test","sa","");
			statement = conn.createStatement();
		}catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	@AfterAll
	public static void close() {
		try {
			conn.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test 
	final void savePlayer() {
		playerRepository.save(player);
		
		assertThat(newPlayerExistsBySQL("nameTest"));
	}
	@Test
	final void savePlayerAndFindById() {
		playerRepository.save(player);
		String id = player.getPlayerId();
		
		assertThat(newPlayerExistsBySQL("nameTest"));
		assertThat(findPlayerByIdBySql(id).equals(player));
	}

	@Test
	final void savePlayerAndFindByName() {
		playerRepository.save(player);
		String name = player.getName();
		
		assertThat(newPlayerExistsBySQL("nameTest"));
		assertThat(findPlayerByNameBySql(name).equals(player));
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
	
	private boolean newPlayerExistsBySQL(String name) {
		boolean result = false;
		String sql = "SELECT * FROM players WHERE name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(sql);
			if (rs!=null)
				result = true;
			else
				result = false;
			rs.close();
		}catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
	
	private Player findPlayerByIdBySql(String id) {
		Player player = new Player();
		String sql = "SELECT * FROM players WHERE playerId='"+id+"'";
		try {
			ResultSet rs = statement.executeQuery(sql);
			player = (Player) rs.getObject(0);
		}catch (Exception e) {
			e.getStackTrace();
		}
		return player;
	}
	private Player findPlayerByNameBySql(String name) {
		Player player = new Player();
		String sql = "SELECT * FROM players WHERE name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(sql);
			player = (Player) rs.getObject(0);
		}catch (Exception e) {
			e.getStackTrace();
		}
		return player;
	}
}

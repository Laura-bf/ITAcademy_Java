package com.diceGame.mysql.model.persistance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diceGame.mysql.model.domain.Player;

@Repository
public interface PlayerMysqlRepository extends JpaRepository<Player,Integer> {
	
	public Player findByName(String name);
	public Optional<Player> findById(Integer id);
	public void deleteByName(String userName);
}

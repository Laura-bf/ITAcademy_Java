package com.diceGame.model.persistance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diceGame.model.domain.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
	
	public Player findByName(String name);
	public Optional<Player> findById(Integer id);
	
}

package com.diceGame.model.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diceGame.model.domain.Player;

public interface PlayerRepository extends JpaRepository<Player,Integer> {
	
	public Player findByName(String name);
	public Optional<Player> findById(Integer id);
	
}

package com.diceGame.nivel3.persistance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diceGame.nivel3.domain.entities.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
	
	public boolean existsByPlayerId(Integer playerId);
	public boolean existsByName(String name);
	
	public List<Player> findByPlayerId(Integer playerId);
	public List<Player> findByName(String name);
	
	public void deleteByPlayerId(Integer playerId);
}

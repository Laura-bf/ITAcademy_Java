package com.diceGame.model.persistance;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diceGame.model.domain.Player;

@Repository
@Profile({"mysql","h2"})
public interface PlayerRepository extends JpaRepository<Player,String> {
	
	public Player findByName(String name);
	public Optional<Player> findById(String id);
	
}

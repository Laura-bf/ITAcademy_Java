package com.diceGame.model.persistance;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.diceGame.model.domain.Player;

@Profile("mongodb")
public interface PlayerMongoRepository extends MongoRepository<Player,Integer>{

	Player findByName(String name);
	Optional<Player> findById(Integer id);
}

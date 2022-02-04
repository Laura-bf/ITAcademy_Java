package com.diceGame.model.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.diceGame.model.domain.MongoPlayer;

@Repository
@Profile("mongodb")
public interface MongoPlayerRepository extends MongoRepository<MongoPlayer,String>{

	MongoPlayer findByName(String name);
	<S extends MongoPlayer> S insert(S entity);
	Optional<MongoPlayer> findById(String id);
	<S extends MongoPlayer> S save(S entity);
	List<MongoPlayer> findAll();
}

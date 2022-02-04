package com.diceGame.nivel3.persistance.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.diceGame.nivel3.domain.documents.Roll;

@Repository
public interface RollRepository extends MongoRepository<Roll,Integer> {

	public List<Roll> findRollByPlayerId(Integer playerId);
	public void deleteByPlayerId(Integer playerId);
}

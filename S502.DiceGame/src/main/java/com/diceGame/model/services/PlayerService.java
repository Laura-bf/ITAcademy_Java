package com.diceGame.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.diceGame.model.domain.Player;
import com.diceGame.model.domain.Roll;

@Service
public interface PlayerService {
	
//	boolean checkNameExists(String name);
//	void checkPasswordFormat(String password);
	
	void addPlayer(Player player);
	void addRegisteredPlayer(String name, String password);
	Player getPlayerById(Integer id);
	Player getPlayerByName(String name);
	List<Player> getAllPlayers();
	void setAnonymousPlayer(Integer playerId);
	void addRoll(Integer playerId, Roll roll);
	List<Roll> getAllRolls(Integer playerId);
	void deleteAllRolls(Integer playerId);
	
	Double getPlayersRanking();
	List<Player> getAllPlayersSortedByRate();
	List<Player> getLoserPlayer();
	List<Player> getWinnerPlayer();
}

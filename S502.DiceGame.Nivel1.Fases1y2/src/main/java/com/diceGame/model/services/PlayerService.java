package com.diceGame.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.diceGame.model.DTO.PlayerDTO;
import com.diceGame.model.domain.Roll;

@Service
public interface PlayerService{
	
	void addPlayer(PlayerDTO playerDTO);
	
	PlayerDTO getPlayerById(String playerId);
	PlayerDTO getPlayerByName(String name);
	
	List<PlayerDTO> getAllPlayers();
	
	void setAnonymousPlayer(PlayerDTO playerDTO);
	
	void playRoll(String playerId);
	
	List<Roll> getAllRolls(String playerId);
	void deleteAllRolls(String playerId);
	
	double getPlayersRanking();
	List<PlayerDTO> getAllPlayersSortedByRate();
	List<PlayerDTO> getLoserPlayer();
	List<PlayerDTO> getWinnerPlayer();
}

package com.diceGame.model.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.diceGame.model.DTO.PlayerDTO;
import com.diceGame.model.domain.Roll;

@Service
public interface PlayerService {
	
	void addPlayer(PlayerDTO playerDTO);
	
	PlayerDTO getPlayerById(Integer id);
	PlayerDTO getPlayerByName(String name);
	List<PlayerDTO> getAllPlayers();
	
	void setAnonymousPlayer(PlayerDTO playerDTO);
	
	void playRoll(Integer playerId);
	
	List<Roll> getAllRolls(Integer playerId);
	void deleteAllRolls(Integer playerId);
	
	double getPlayersRanking();
	List<PlayerDTO> getAllPlayersSortedByRate();
	List<PlayerDTO> getLoserPlayer();
	List<PlayerDTO> getWinnerPlayer();
}

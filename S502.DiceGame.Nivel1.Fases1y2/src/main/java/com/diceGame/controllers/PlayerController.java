package com.diceGame.controllers;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diceGame.model.DTO.PlayerDTO;
import com.diceGame.model.services.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {
	
	private final PlayerService playerService;

	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@PostMapping
	public ResponseEntity<?> addPlayer(@RequestBody PlayerDTO dto){
		ResponseEntity<?> result = null;
		try {
			playerService.addPlayer(dto);
			result = ResponseEntity.status(HttpStatus.CREATED).build();
		}catch(IllegalArgumentException e) {
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}

	@PutMapping
	public ResponseEntity<?> changeName(@RequestBody PlayerDTO dto) {

		ResponseEntity<?> result = null;
		try {
			playerService.setAnonymousPlayer(dto);
			result = ResponseEntity.status(HttpStatus.OK).build();
		}catch(NoSuchElementException e) {
			result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}
	
	@PostMapping("/{id}/rolls")  
//	el enunciado pone games pero he estado usando roll para referirme a las tiradas de dados as?? que dejo rolls aqu??
	public ResponseEntity<?> playGame(@PathVariable("id") String playerId) {	

		ResponseEntity<?> result = null;
		try {
			playerService.playRoll(playerId);
			result = ResponseEntity.status(HttpStatus.OK).body(playerService.getPlayerById(playerId));
		}catch(NoSuchElementException e) {
			result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}
	
	@DeleteMapping("/{id}/rolls") 
	public ResponseEntity<?> deleteRolls(@PathVariable("id") String playerId) {
		ResponseEntity<?> result = null;
		try {
			playerService.deleteAllRolls(playerId);
			result = ResponseEntity.status(HttpStatus.OK).body(playerService.getPlayerById(playerId));
		}catch(NoSuchElementException e) {
			result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}
	@GetMapping
	public ResponseEntity<?> getAllPlayers() {

		ResponseEntity<?> result = null;
		try {
			result = ResponseEntity.status(HttpStatus.OK).body(playerService.getAllPlayers());
		} catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}
	@GetMapping("/{id}/rolls")
	public ResponseEntity<?> getAllPlayerRolls(@PathVariable("id") String playerId) {
		ResponseEntity<?> result = null;
		try {
			result = ResponseEntity.status(HttpStatus.OK).body(playerService.getAllRolls(playerId));
		}catch(NoSuchElementException e) {
			result = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}
	@GetMapping("/ranking")
	public ResponseEntity<?> getPlayersRanking(){
		ResponseEntity<?> result = null;
		try {
			result = ResponseEntity.status(HttpStatus.OK).body(playerService.getPlayersRanking());
		}catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}
	@GetMapping("/ranking/loser")
	public ResponseEntity<?> getLosers(){
		ResponseEntity<?> result = null;
		try {
			result = ResponseEntity.status(HttpStatus.OK).body(playerService.getLoserPlayer());
		}catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}
	@GetMapping("/ranking/winner")
	public ResponseEntity<?> getWinners(){
		ResponseEntity<?> result = null;
		try {
			result = ResponseEntity.status(HttpStatus.OK).body(playerService.getWinnerPlayer());
		}catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}
}

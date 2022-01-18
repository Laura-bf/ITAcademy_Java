package com.diceGame.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.diceGame.model.domain.Player;
import com.diceGame.model.services.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;
	
	@PostMapping
	public ResponseEntity<?> addPlayer(@RequestParam("name") String name,@RequestParam("password") String password) {

		ResponseEntity<?> result = null;
		Player player = new Player(name,password);
		try {
			playerService.addPlayer(player);
			result = ResponseEntity.status(HttpStatus.OK).body(player);
		} catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}
	@PutMapping
	public ResponseEntity<?> changeName(@RequestParam("playerId") Integer playerId) {

		ResponseEntity<?> result = null;
		Player player = playerService.getPlayerById(playerId);
		try {
			playerService.setAnonymousPlayer(player);
			result = ResponseEntity.status(HttpStatus.OK).body(player);
		} catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}
	@PostMapping("/{id}/rolls")  
//	el enunciado pone games pero he estado usando roll para referirme a las tiradas de dados así que dejo rolls aquí
//	no funciona: object references an unsaved transient instance **** creo que se arregla haciendo un repository de rolls pq con cascadeType.all no se arregló***!!!***
	public ResponseEntity<?> playGame(@PathVariable("id") Integer playerId) {

		ResponseEntity<?> result = null;
		try {
			playerService.playRoll(playerId);
			result = ResponseEntity.status(HttpStatus.OK).body(playerService.getPlayerById(playerId));
		} catch (Exception ex) {
			result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}
		return result;
	}
	
	@DeleteMapping("/{id}/rolls") 
	public ResponseEntity<?> deleteRolls(@PathVariable(name="id") Integer playerId) {

		ResponseEntity<?> result = null;
		try {
			playerService.deleteAllRolls(playerId);
			result = ResponseEntity.status(HttpStatus.OK).body(playerService.getPlayerById(playerId));
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
	public ResponseEntity<?> getAllPlayerRolls(@PathVariable(name="id") Integer playerId) {

		ResponseEntity<?> result = null;
		try {
			result = ResponseEntity.status(HttpStatus.OK).body(playerService.getAllRolls(playerId));
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

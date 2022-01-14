package com.diceGame.model.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diceGame.model.domain.Player;
import com.diceGame.model.domain.Roll;
import com.diceGame.model.repositories.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService{
	
	@Autowired
	PlayerRepository playerRepository;

	private boolean checkNameExists(String name) {
		if(name.equals(""))
			throw new IllegalArgumentException("Es necesario indicar un nombre");
		if(playerRepository.findByName(name)==null)
			return false;
		else
			return true;
	}
	private void checkPasswordFormat(String password) {
		final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,15}$";
	    final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	    Matcher matcher = pattern.matcher(password);
	    if(!matcher.matches())   
	    	throw new IllegalArgumentException("Contraseña requiere:\n-Entre 8 y 15 caracteres con al menos un dígito,una mayúscula,una minúscula y un caracter especial.No admite espacios en blanco");
	}

	@Override
	public void addPlayer(Player player) {
		playerRepository.save(player);	
	}
	@Override
	public void addRegisteredPlayer(String name, String password) {
		Player player = null;
		this.checkPasswordFormat(password);
		if(checkNameExists(name))
			throw new IllegalArgumentException("Este nombre ya está registrado");
		else {
			player = new Player(name,password);
			playerRepository.save(player);
		}
	}

	@Override
	public Player getPlayerById(Integer id) {
		Player player = playerRepository.findById(id).get();
		if(player.equals(null))
			throw new NoSuchElementException("No existe ningún jugador con este id");
		return player;
	}

	@Override
	public Player getPlayerByName(String name) {
		Player player = null;
		if(checkNameExists(name))
			player = playerRepository.findByName(name);
		if(player==null)
			throw new NoSuchElementException("No existe ningún jugador con este nombre");
		return player;
	}

	@Override
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}
	
	@Override
	public void setAnonymousPlayer(Integer playerId) {
		Player player = playerRepository.findById(playerId).get();
		player.setName("Anonymous");
		playerRepository.save(player);
	}

	@Override
	public void addRoll(Integer playerId, Roll roll) {
		Player player = playerRepository.findById(playerId).get();
		player.addRoll(roll);
		this.calculateRate(player);
		playerRepository.save(player);
	}

	@Override
	public List<Roll> getAllRolls(Integer playerId) {
		return playerRepository.findById(playerId).get().getRollList();
	}

	@Override
	public void deleteAllRolls(Integer playerId) {
		Player player = playerRepository.findById(playerId).get();
		player.deleteAllRollsFromList();
		this.calculateRate(player);
		playerRepository.save(player);
		
	}

	@Override
	public List<Player> getLoserPlayer() {
		List<Player> allPlayers = playerRepository.findAll();
		List<Double> rates = allPlayers.stream().map(p -> p.getRate()).collect(Collectors.toList());
		List<Player> losers = new ArrayList<Player>();
		double min = Collections.min(rates);
		for(Player p : allPlayers) {
			if(p.getRate()==min)
				losers.add(p);
		}
		
		return losers;
	}

	@Override
	public List<Player> getWinnerPlayer() {
		List<Player> allPlayers = playerRepository.findAll();
		Collections.sort(allPlayers);
		double max = allPlayers.get(0).getRate();
		
		return allPlayers.stream().filter(p -> p.getRate()==max).collect(Collectors.toList());
	}
	@Override
	public List<Player> getAllPlayersSortedByRate() {
		List<Player> allPlayers = playerRepository.findAll();
		Collections.sort(allPlayers);
		return allPlayers;
	}

	@Override
	public Double getPlayersRanking() {
		List<Player> allPlayers = playerRepository.findAll();
		long totalWon = allPlayers.stream().map(p -> p.getRollList().stream().filter(r -> r.isWon())).count();
		long totalLost = allPlayers.stream().map(p -> p.getRollList().stream().filter(r -> !r.isWon())).count();
		long totalRolls = totalWon+totalLost;
		
		return (totalWon/totalRolls)*100d;
	}
	
	private void calculateRate(Player player) {
		List<Roll> rollList = player.getRollList();
		double totalWins = 0;
		double size = rollList.size();
		for (Roll roll : rollList) {
			if(roll.isWon())
				totalWins+=1;
		}
		double result = (totalWins/size)*100;
		player.setRate(result);
	}

}

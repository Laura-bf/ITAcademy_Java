package com.diceGame.nivel3.model.services;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.diceGame.nivel3.domain.documents.Roll;
import com.diceGame.nivel3.domain.entities.Player;
import com.diceGame.nivel3.domain.models.PlayerDTO;
import com.diceGame.nivel3.domain.models.RollDTO;
import com.diceGame.nivel3.persistance.repositories.PlayerRepository;
import com.diceGame.nivel3.persistance.repositories.RollRepository;

@Service
public class PlayerService implements UserDetailsService{

	@Autowired
	RollRepository rollRepository;
	
	@Autowired
	PlayerRepository playerRepository;
	
	public PlayerService() {
		
	}
	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Player player = playerRepository.findByName(username).get(0);
		if (player == null) {
			throw new UsernameNotFoundException(username);
		}
		String password = player.getPassword();
		
		return new User(player.getName(), "{noop}"+password, emptyList());
		
	}
	
	public String createPlayer(PlayerDTO playerDto) {
		if(!playerRepository.findByName(playerDto.getName()).isEmpty()) {
			throw new IllegalArgumentException("Este nombre ya está registrado");
		}else {
			checkPasswordFormat(playerDto.getPassword());
			Player player = new Player(playerDto.getName(),playerDto.getPassword());
			playerRepository.save(player);
			
			return "Jugador registrado correctamente";
		}
	}

//este método se utiliza en acciones de seguridad de los recursos
	public Integer getPlayerIdByName(String username) {
		if(!playerRepository.findByName(username).isEmpty())
			return playerRepository.findByName(username).get(0).getPlayerId();
		else 
			return null;
	}
//este método se utiliza en acciones de seguridad de los recursos	
	public String getPlayerNameById(Integer id) {
		return playerRepository.findById(id).get().getName();
	}
	
	public List<PlayerDTO> getAllPlayers(){
		List<PlayerDTO> players = new ArrayList<PlayerDTO>();
		List<Player> playerList = new ArrayList<Player>();
		playerList = playerRepository.findAll();
		
		if(playerList.size()>0) { 
			playerList.stream().forEach(p -> {
				PlayerDTO playerDto = new PlayerDTO();
				playerDto.setPlayerId(p.getPlayerId());
				playerDto.setName(p.getName());
				playerDto.setVisibleName(p.getVisibleName());
				playerDto.setPassword(p.getPassword());
				playerDto.setRate(p.getRate());
				List<Roll> rollList = new ArrayList<Roll>();
				List<RollDTO> rolls = new ArrayList<RollDTO>();
				try {
					rollList = rollRepository.findRollByPlayerId(p.getPlayerId());
				}catch(Exception e) {
					throw e;
				}
				if(rollList.size()>0) {
					rollList.stream().forEach(r -> {
						RollDTO rollDto = new RollDTO();
						BeanUtils.copyProperties(r, rollDto);
						rolls.add(rollDto);
					});
				}
				playerDto.setRollList(rolls);
				players.add(playerDto);
			});
		}
		return players;
	}
	
	public String changeToAnonymous(PlayerDTO playerDto) {
		if(playerRepository.existsByName(playerDto.getName())) {
			Player player = playerRepository.findByName(playerDto.getName()).get(0);
			player.setVisibleName("Anonymous");
			playerRepository.save(player);
			
			return "Jugador anónimo - el nombre ya no es visible ";
		}else
			throw new NoSuchElementException("Jugador no encontrado");
	}
	
	@Transactional
	public List<RollDTO> getPlayerRolls(Integer playerId){
		List<RollDTO> rolls = new ArrayList<RollDTO>();
		List<Roll> rollList = new ArrayList<Roll>();
		if(playerRepository.existsById(playerId)) {
			rollList = rollRepository.findRollByPlayerId(playerId);
			if(rollList.size()>0) {
				rollList.stream().forEach(r -> {
					RollDTO rollDto = new RollDTO();
					BeanUtils.copyProperties(r, rollDto);
					rolls.add(rollDto);
				});
			}
		}else
			throw new NoSuchElementException("Jugador no encontrado");
		
		return rolls;
	}
	@Transactional
	public String playRoll(Integer playerId) {
		if(playerRepository.existsByPlayerId(playerId)) {
			Roll roll = new Roll();
			roll.playRoll();
			roll.setPlayerId(playerId);
			rollRepository.save(roll);
			List<RollDTO> rolls = getPlayerRolls(playerId);
			PlayerDTO playerDto = new PlayerDTO();
			BeanUtils.copyProperties(playerRepository.findByPlayerId(playerId).get(0), playerDto);
			playerDto.setRollList(rolls);
			playerDto.calculateRate();
			playerRepository.findByPlayerId(playerId).get(0).setRate(playerDto.getRate());
			if(roll.isWon())
				return "Partida ganada!!";
			else
				return "Partida perdida!!";
		}else
			throw new NoSuchElementException("Jugador no encontrado");
	}

	@Transactional
	public String deletePlayerRolls(Integer playerId) {
		if(playerRepository.existsByPlayerId(playerId)) {
			rollRepository.deleteByPlayerId(playerId);
			playerRepository.getById(playerId).setRate(0d);
			return "Rolls removed successfully";
		}else
			throw new NoSuchElementException("Jugador no encontrado");
	}
	
	public double getPlayersRanking() {
		double rate = playerRepository.findAll().stream().mapToDouble(p -> p.getRate()).sum();
		return rate/playerRepository.findAll().size();
	}
	
	public List<PlayerDTO> getWinnerPlayer() {
		List<PlayerDTO> dtos = getAllPlayers();
		List<Double> rates = dtos.stream().map(p -> p.getRate()).collect(Collectors.toList());
		double max = Collections.max(rates);
		
		return dtos.stream().filter(p -> p.getRate()==max).collect(Collectors.toList());
	}
	
	public List<PlayerDTO> getLoserPlayer() {
		List<PlayerDTO> dtos = getAllPlayers();
		List<Double> rates = dtos.stream().map(p -> p.getRate()).collect(Collectors.toList());
		double min = Collections.min(rates);

		return dtos.stream().filter(p -> p.getRate()==min).collect(Collectors.toList());
	}
	
	private void checkPasswordFormat(String password) {
		final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,15}$";
	    final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	    Matcher matcher = pattern.matcher(password);
	    if(!matcher.matches())   
	    	throw new IllegalArgumentException("Contraseña requiere:\n-Entre 8 y 15 caracteres con al menos un dígito,una mayúscula,una minúscula y un caracter especial.No admite espacios en blanco");
	}
}

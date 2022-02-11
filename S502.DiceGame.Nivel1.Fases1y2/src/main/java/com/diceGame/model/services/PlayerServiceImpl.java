package com.diceGame.model.services;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.diceGame.model.DTO.PlayerDTO;
import com.diceGame.model.domain.Player;
import com.diceGame.model.domain.Roll;
import com.diceGame.model.persistance.PlayerRepository;

@Service
@Primary
@Profile({"mysql","h2"})
public class PlayerServiceImpl implements PlayerService{

	private PlayerRepository playerRepository;
	
	@Autowired
	public PlayerServiceImpl(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	@Override
	public void addPlayer(PlayerDTO playerDTO) {
		checkPasswordFormat(playerDTO.getPassword());
		if(checkNameExists(playerDTO.getName()))
			throw new IllegalArgumentException("Este nombre ya está registrado");
		else {
			playerRepository.save(mapDtoToEntity(playerDTO));
		}
	}
	
	@Override
	public PlayerDTO getPlayerById(String id) {
		Optional<Player> player = playerRepository.findById(id);
		if(!player.isPresent())
			throw new NoSuchElementException("No existe ningún jugador con este id");
		else
			return mapEntityToDto(player.get());
	}

	@Override
	public PlayerDTO getPlayerByName(String name) {
		Player player = null;
		if(checkNameExists(name))
			player = playerRepository.findByName(name);
		if(player==null)
				throw new NoSuchElementException("No existe ningún jugador con este nombre");
		else
			return mapEntityToDto(player);
	}

	@Override
	public List<PlayerDTO> getAllPlayers() {
		List<PlayerDTO> dtos = playerRepository.findAll().stream().map(p -> mapEntityToDto(p)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public void setAnonymousPlayer(PlayerDTO playerDTO) {
		playerDTO.setVisibleName("Anonymous");
		Player player = playerRepository.findByName(playerDTO.getName());
		if(player!=null) {
			player.setVisibleName("Anonymous");
			playerRepository.save(player);
		} else
			throw new NoSuchElementException("Jugador no encontrado");
	}
	
	@Override
	public void playRoll(String id) {
		Player player = playerRepository.findById(id).get();
		if(player != null) {
			player.addRoll();
			playerRepository.save(player);
		} else
			throw new NoSuchElementException("No existe ningún jugador con este id");
	}
	
	@Override
	public List<Roll> getAllRolls(String playerId) {
		Player player = playerRepository.findById(playerId).get();
		if(player != null) 
			return player.getRollList();
		else
			throw new NoSuchElementException("No existe ningún jugador con este id");
	}
	
	@Override
	public void deleteAllRolls(String playerId) {	
		Player player = playerRepository.findById(playerId).get();
		if(player != null) {
			player.deleteAllRollsFromList();
			playerRepository.save(player);
		}else
			throw new NoSuchElementException("No existe ningún jugador con este id");
	}

	@Override
	public List<PlayerDTO> getLoserPlayer() {
		List<PlayerDTO> dtos = getAllPlayers();
		List<Double> rates = dtos.stream().map(p -> p.getRate()).collect(Collectors.toList());
		double min = Collections.min(rates);

		return dtos.stream().filter(p -> p.getRate()==min).collect(Collectors.toList());
	}

	@Override
	public List<PlayerDTO> getWinnerPlayer() {
		List<PlayerDTO> dtos = getAllPlayers();
		List<Double> rates = dtos.stream().map(p -> p.getRate()).collect(Collectors.toList());
		double max = Collections.max(rates);
		
		return dtos.stream().filter(p -> p.getRate()==max).collect(Collectors.toList());
	}
	
	@Override
	public List<PlayerDTO> getAllPlayersSortedByRate() {
		List<Player> allPlayers = playerRepository.findAll();
		Collections.sort(allPlayers);
		return allPlayers.stream().map(p -> mapEntityToDto(p)).collect(Collectors.toList());
	}

	@Override
	public double getPlayersRanking() {
		double rate = playerRepository.findAll().stream().mapToDouble(p -> p.getRate()).sum();
		return rate/playerRepository.findAll().size();
	}
	
	private PlayerDTO mapEntityToDto(Player player) {
		PlayerDTO dto = new PlayerDTO();
		dto.setPlayerId(player.getPlayerId());
		dto.setName(player.getName());
		dto.setVisibleName(player.getVisibleName());
		dto.setPassword(player.getPassword());
		dto.setRate(player.getRate());
		dto.setRollList(player.getRollList());
		return dto;
	}
	
	private Player mapDtoToEntity(PlayerDTO dto) {
		Player player = new Player();
		if(dto.getPlayerId()!=null)
			player.setPlayerId(dto.getPlayerId());
		player.setName(dto.getName());
		player.setVisibleName(dto.getName());
		player.setPassword(dto.getPassword());
		player.setRate(dto.getRate());
		player.setRollList(dto.getRollList());
		return player;
	}
	
	protected boolean checkNameExists(String name) {
		if(name.equals(""))
			throw new IllegalArgumentException("Es necesario indicar un nombre");
		if(playerRepository.findByName(name)==null)
			return false;
		else
			return true;
	}
	
	protected void checkPasswordFormat(String password) {
		final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,15}$";
	    final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
	    Matcher matcher = pattern.matcher(password);
	    if(!matcher.matches())   
	    	throw new IllegalArgumentException("Contraseña requiere:\n-Entre 8 y 15 caracteres con al menos un dígito,una mayúscula,una minúscula y un caracter especial.No admite espacios en blanco");
	}
	
}

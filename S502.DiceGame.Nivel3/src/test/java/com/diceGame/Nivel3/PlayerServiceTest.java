package com.diceGame.Nivel3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.diceGame.nivel3.Application;
import com.diceGame.nivel3.domain.documents.Roll;
import com.diceGame.nivel3.domain.entities.Player;
import com.diceGame.nivel3.domain.models.PlayerDTO;
import com.diceGame.nivel3.domain.models.RollDTO;
import com.diceGame.nivel3.model.services.PlayerService;
import com.diceGame.nivel3.persistance.repositories.PlayerRepository;
import com.diceGame.nivel3.persistance.repositories.RollRepository;

@SpringBootTest(classes = {Application.class})
@TestInstance(Lifecycle.PER_CLASS)
@Transactional //para que no de error de failed to laizily inititalize (The problem is caused by accessing an attribute with the hibernate session closed)
class PlayerServiceTest {
	
	@InjectMocks
	PlayerService playerServiceMock;
	
	@MockBean
	PlayerRepository playerRepoMock;
	
	@MockBean
	RollRepository rollRepoMock;
	
	private Player player;
	private PlayerDTO playerDto;
	private List<RollDTO> rollDtos;
	
	
	@BeforeAll
	void init() {
		this.player = new Player("nombre", "Aa123456!");
		this.playerDto = new PlayerDTO();
		BeanUtils.copyProperties(player, playerDto);
		this.rollDtos = new ArrayList<RollDTO>();
		Roll roll1 = new Roll();
		Roll roll2 = new Roll();
		roll1.playRoll();
		roll1.setWon(true);
		roll2.playRoll();
		roll2.setWon(true);
		RollDTO rollDto1 = new RollDTO();
		RollDTO rollDto2 = new RollDTO();
		BeanUtils.copyProperties(roll1, rollDto1);
		BeanUtils.copyProperties(roll2, rollDto2);
		rollDtos.add(rollDto1);
		rollDtos.add(rollDto2);
		playerDto.setRollList(rollDtos);
	}

    @Test
	final void addPlayer_OK() {
    	when(playerRepoMock.save(player)).thenReturn(player);
    	
    	playerServiceMock.createPlayer(new PlayerDTO("nombre", "Aa123456!"));
    	
    	//como apuntan a referencias distintas no pasa el test si no se usa argumentCaptor!!!!!
    	ArgumentCaptor<Player> playerArgumentCaptor = ArgumentCaptor.forClass(Player.class);
    	
    	verify(playerRepoMock).save(playerArgumentCaptor.capture());
    	Player savedPlayer = playerArgumentCaptor.getValue();
    	assertEquals("nombre", savedPlayer.getName());
	}
	
    @Test
	final void addPlayer_NameAlreadyRegistered_throwsIllegalArgumentException() {
    	List<Player> players = new ArrayList<Player>();
    	players.add(player);
		when(playerRepoMock.findByName("nombre")).thenReturn(players);
		
		try {
			playerServiceMock.createPlayer(new PlayerDTO("nombre", "Aa123456!"));
			fail("Exception expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("Este nombre ya está registrado"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}
    
    @Test
	final void addPlayer_EmptyName_ThrowsIllegalArgumentException() {
		try {
			playerServiceMock.createPlayer(new PlayerDTO("", "Aa123456!"));
			fail("Exception expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("Es necesario indicar un nombre"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}
    
    private Stream<Arguments> provideStringsForWrongFormatPW() {
        return Stream.of(
          Arguments.of("Aa3456!", "8 char minimum"),
          Arguments.of("Aa123456789123456!", "15 char maximum"),
          Arguments.of("Aaaaaaaa!", "At least one digit required"),
          Arguments.of("aa123456!", "Capital letter required"),
          Arguments.of("AA123456!", "Lowercase letter required"),
          Arguments.of("Aa123456", "Special char required")
        );
    }
    
    @ParameterizedTest
    @MethodSource("provideStringsForWrongFormatPW")
	final void addPlayer_WrongFormatPW_throwsIllegalArgumentException(String input, String message) {
		try {
			playerServiceMock.createPlayer(new PlayerDTO("nombre", input));
			fail("Exception expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("Contraseña requiere:\n-Entre 8 y 15 caracteres con al menos un dígito,una mayúscula,una minúscula y un caracter especial.No admite espacios en blanco"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}
	
	@Test
	final void getAllPlayers() {
		Player player1 = new Player("test1","Aa123456!");
		player1.setPlayerId(1);
		Player player2 = new Player("test2","Aa123456!");
		player2.setPlayerId(2);
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);

		when(playerRepoMock.findAll()).thenReturn(playerList);
		
		List<Integer> result = new ArrayList<Integer>();
		playerServiceMock.getAllPlayers().stream().map(p->p.getPlayerId()).forEach(result::add);
		
		assertThat(Integer.valueOf(result.get(0))==1);
		assertThat(Integer.valueOf(result.get(1))==2);
		assertThat(result.size()==2);
	}
	@Test
	final void setPlayer_Anonymous() {
		when(playerRepoMock.findByName("nombre").get(0)).thenReturn(player);
	
		playerServiceMock.changeToAnonymous(new PlayerDTO("nombre", "Aa123456!"));
		
		assertEquals("Anonymous", player.getVisibleName());
		assertEquals("nombre", player.getName());
	}
	
	@Test
	@Transactional
	final void getAllRolls_ByPlayerId_returnListOfRolls() {
		
		when(playerRepoMock.findById(1)).thenReturn(Optional.of(player));
		
		assertEquals(this.rollDtos, playerServiceMock.getPlayerRolls(1));
	}
	
	@Test
	@Transactional
	final void playRoll_ByPlayerId() {
		when(playerRepoMock.findById(1)).thenReturn(Optional.of(player));
		
		playerServiceMock.playRoll(1);
		
		verify(playerRepoMock).findById(1);
		assertTrue(playerDto.getRollList().size()>0);
	}
	
	@Test
	@Transactional
	final void deleteAllRolls_ByPlayerId() {
		when(playerRepoMock.findById(1)).thenReturn(Optional.of(player));	
		playerServiceMock.deletePlayerRolls(1);
		
		assertTrue(rollDtos.size()==0);
	}
	
	@Test
	final void getLoser_OneLoser() {
		Player player1 = new Player("test1", "Aa123456!");
		Player player2 = new Player("test2", "Aa123456!");
		player1.setRate(90d);
		player2.setRate(40d);
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		
		when(playerRepoMock.findAll()).thenReturn(playerList);
		
		assertEquals(player2.getName(), playerServiceMock.getLoserPlayer().get(0).getName());
	}
	
	@Test
	final void getLoser_ManyLosers() {
		Player player1 = new Player("test1", "Aa123456!");
		Player player2 = new Player("test2", "Aa123456!");
		Player player3 = new Player("test3", "Aa123456!");
		player1.setRate(90d);
		player2.setRate(40d);
		player3.setRate(40d);
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		
		when(playerRepoMock.findAll()).thenReturn(playerList);
		
		List<String> expected = new ArrayList<String>();
		expected.add("test2");
		expected.add("test3");
		
		List<String> result = new ArrayList<String>();
		playerServiceMock.getLoserPlayer().stream().map(p->p.getName()).forEach(result::add);
		
		assertEquals(expected, result);
	}
	
	@Test
	final void getWinner_OneWinner() {
		Player player1 = new Player("test1", "Aa123456!");
		Player player2 = new Player("test2", "Aa123456!");
		player1.setRate(90d);
		player2.setRate(40d);
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		
		when(playerRepoMock.findAll()).thenReturn(playerList);
		
		assertEquals(player1.getName(), playerServiceMock.getWinnerPlayer().get(0).getName());
	}
	
	@Test
	final void getWinner_ManyWinners() {
		Player player1 = new Player("test1", "Aa123456!");
		Player player2 = new Player("test2", "Aa123456!");
		Player player3 = new Player("test3", "Aa123456!");
		player1.setRate(90d);
		player2.setRate(90d);
		player3.setRate(40d);
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		
		when(playerRepoMock.findAll()).thenReturn(playerList);
		
		List<String> expected = new ArrayList<String>();
		expected.add("test1");
		expected.add("test2");
		
		List<String> result = new ArrayList<String>();
		playerServiceMock.getWinnerPlayer().stream().map(p->p.getName()).forEach(result::add);
		
		assertEquals(expected, result);
	}
	
	@Test
	final void getPlayersRanking() {
		player.setRate(100d);
		Player player2 = new Player("test2", "Aa123456!");
		player2.setRate(50d);
		
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(player);
		playerList.add(player2);
		
		when(playerRepoMock.findAll()).thenReturn(playerList);
		double rankingExpected = 75d;
		
		assertEquals(rankingExpected, playerServiceMock.getPlayersRanking());
	}
}

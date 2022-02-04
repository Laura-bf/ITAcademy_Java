package com.diceGame;

import com.diceGame.model.DTO.PlayerDTO;
import com.diceGame.model.services.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@TestInstance(Lifecycle.PER_CLASS)
class PlayerControllerTest {
//
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	PlayerService playerService;

	@Test
	final void POST_AddPlayer_OK() throws Exception {
		PlayerDTO dto = new PlayerDTO("nombre","Aa1234656*");
		ObjectMapper obMap = new ObjectMapper();
		String dtoJson = obMap.writeValueAsString(dto);
		
		mockMvc.perform(post("/players")
				.contentType(MediaType.APPLICATION_JSON)
				.content(dtoJson))
				.andExpect(status().isCreated());
	}

	@Test
	final void POST_VisbibleNameChangesToAnonymous_OK() throws Exception {
		PlayerDTO dto = new PlayerDTO("nombre","Aa1234656*");
		List<PlayerDTO> dtos = new ArrayList<PlayerDTO>();
		dtos.add(dto);
		ObjectMapper obMap = new ObjectMapper();
		String dtoJson = obMap.writeValueAsString(dto);
		when(playerService.getAllPlayers()).thenReturn(dtos);
		
		mockMvc.perform(put("/players")
				.contentType(MediaType.APPLICATION_JSON)
				.content(dtoJson))
				.andExpect(status().isOk());
	}

	@Test
	final void POST_PlayGame_RollToPlayer_ById_OK() throws Exception {
		 // Setup our mocked service
		PlayerDTO dto = new PlayerDTO("nombre","Aa1234656*");
		dto.setPlayerId("1");
		when(playerService.getPlayerById("1")).thenReturn(dto);

        // Execute the POST request
		mockMvc.perform(post("/{id}/rolls", "1"))
				// Validate the response code and content type
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				// Validate the returned fields
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.name", is("nombre")))
                .andExpect(jsonPath("$.rollList").isNotEmpty());
	}

//	@Test
//	final void testDeleteRolls() {
//		fail("Not yet implemented"); // TODO
//	}

	@Test
	final void GET_AllPlayersList_OK() throws Exception {
		//given
		PlayerDTO dto1 = new PlayerDTO("nombre","Aa1234566*");
		PlayerDTO dto2 = new PlayerDTO("nombre2","Aa1234566*");
		List<PlayerDTO> dtos = new ArrayList<PlayerDTO>();
		dtos.add(dto1);
		dtos.add(dto2);
		when(playerService.getAllPlayers()).thenReturn(dtos);
	
		mockMvc.perform(get("/players"))
			.andExpect(status().isOk())
			.andExpect(content().json("[{},{}]"));
	}

//	@Test
//	final void testGetAllPlayerRolls() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	final void testGetPlayersRanking() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	final void testGetLosers() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	final void testGetWinners() {
//		fail("Not yet implemented"); // TODO
//	}

}

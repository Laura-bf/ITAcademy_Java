package com.white_collar.nivel3.controllers;


import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.white_collar.nivel3.model.services.ShopService;
import com.white_collar.nivel3.model.domain.Shop;

@WebMvcTest(ShopController.class)
@ActiveProfiles("test")
class ShopControllerTest {

	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean
	ShopService shopService;
	
	@Test
	void GET_listAllShops_OK() throws Exception {
		List<Shop> shopList = Arrays.asList(
				new Shop("test1", 1),
				new Shop("test2",2),
				new Shop("test3",3)
		);
		when(shopService.getAllShops()).thenReturn(shopList);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/shops"))
				.andExpect(status().isOk())
				.andExpect(content().json("[{},{},{}]"));
		
	}

	@Test
	void POST_newShop_OK() throws Exception {
		List<Shop> shopList = Arrays.asList(
				new Shop("test1", 1),
				new Shop("test2",2),
				new Shop("test3",3),
				new Shop("testName",0)
		);
		when(shopService.getAllShops()).thenReturn(shopList);
		
		mockMvc.perform(
			MockMvcRequestBuilders.post("/shops")
				.param("name", "testName")
				.param("max", "0"))
			.andExpect(status().isCreated())
			.andExpect(content().json("[{},{},{},{}]"));
	}
	
	@Test
	void POST_newShop_NOParams_BAD_REQ() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/shops"))
			.andExpect(status().isBadRequest());	
	}

	@Test
	void POST_newShop_NOName_BAD_REQ() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/shops")
				.param("max", "0"))
			.andExpect(status().isBadRequest());	
	}
	@Test
	void POST_newShop_NOMax_BAD_REQ() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/shops")
				.param("name", "testName"))
			.andExpect(status().isBadRequest());	
	}
	
	@Test
	void GET_listShopPictures_OK() throws Exception {
		shopService.addShop("nameTest", 0);
		int id = shopService.getAllShops().size()-1;
	
		mockMvc.perform(MockMvcRequestBuilders.get("/shops/"+id+"/pictures"))
			.andExpect(status().isOk())
			.andExpect(content().json("[]"));
	}
	
	@Test
	void GET_listShopPictures_WrongId_BAD_REQUEST() throws Exception {
		Shop shop = new Shop("Test",1);

		mockMvc.perform(MockMvcRequestBuilders.get("/shops/"+shop.getId()+"/pictures"))
			.andExpect(status().isBadRequest());
	}

	@Test
	void testAddPictureToShop() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteShopPictures() {
		fail("Not yet implemented");
	}

}

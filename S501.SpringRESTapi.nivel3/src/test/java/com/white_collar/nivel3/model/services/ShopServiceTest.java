package com.white_collar.nivel3.model.services;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.ActiveProfiles;

import com.white_collar.nivel3.model.domain.Picture;
import com.white_collar.nivel3.model.repositories.ShopRepository;

@SpringBootTest
@ActiveProfiles("test")
//@Sql("/data-test.sql")
class ShopServiceTest {

	@Autowired
	ShopRepository shopRepository;
	
	@Autowired
	ShopService shopService;
	
	private static Connection conn;
	private static Statement statement;
	
	@BeforeAll
	public static void init() {
		try {
			Class.forName("org.h2.Driver"); 
			conn = DriverManager.getConnection("jdbc:h2:mem:test","sa","");
			statement = conn.createStatement();
		}catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	@AfterAll
	public static void close() {
		try {
			conn.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testGetAllShops_OK() {
//		//como los datos se han cargado del archivo data-test.sql y este archivo tiene 3 registros:
//		assertTrue(shopService.getAllShops().size()==3);
		//lo mismo pero con query sql por si no estuviera el data-test.sql:
		assertTrue(shopService.getAllShops().size()==this.getAllShopsSizeBySQL());
	}
	
	@Test
	void testAddShop_OK() {
		String name = "testAddShop";
		
		shopService.addShop(name, 0);		
		
		assertTrue(this.newShopExistsBySQL(name));

	}

	@Test
	void testAddPictureIntegerStringString() {
		shopService.addPicture(1, "titleTest","authorTest");
		
		assertEquals("titleTest",shopService.getAllPictures(1).get(0).getPictureName());
		
	}

	@Test
	void testAddPictureIntegerPicture() {
		int initSize = (int)shopRepository.count();
		Picture picture = new Picture("titleTest2","authorTest2");
		shopService.addPicture(initSize-1, picture);
		
		assertEquals("titleTest2",shopService.getAllPictures(initSize-1).get(0).getPictureName());
	}

	@Test
	void testGetAllPictures() {
		int initSize = (int)shopRepository.count();
		shopService.addPicture(initSize-1, "titleTest3","authorTest");
		assertFalse(shopService.getAllPictures(initSize-1).isEmpty());
	}

	@Test
	void testEraseAllPictures() {
		int initSize = (int)shopRepository.count();
		shopService.addPicture(initSize-1, "titleTest3","authorTest");
		shopService.eraseAllPictures(initSize-1);
		assertTrue(shopService.getAllPictures(initSize-1).isEmpty());
	}
	
	private int getAllShopsSizeBySQL() {
		int result = 0;
		try {
			ResultSet rs = statement.executeQuery("SELECT * FROM shops");
			while(rs.next()) {
				result+=1;
			}
			rs.close();
		}catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
	
	private boolean newShopExistsBySQL(String name) {
		boolean result = false;
		String sql = "SELECT * FROM shops WHERE Shop_name='"+name+"'";
		try {
			ResultSet rs = statement.executeQuery(sql);
			if (rs!=null)
				result = true;
			else
				result = false;
			rs.close();
		}catch (Exception e) {
			e.getStackTrace();
		}
		return result;
	}
	
}

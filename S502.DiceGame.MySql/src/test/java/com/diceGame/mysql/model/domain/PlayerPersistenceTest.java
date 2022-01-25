package com.diceGame.mysql.model.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class PlayerPersistenceTest {

	@Autowired
	private TestEntityManager entityManager;
	
	private static Player testPlayer;

	@BeforeAll
	public static void setUp() {
		testPlayer = new Player("testName","Aa123456!");
	}
	
	@Test
	final void savePlayer() {
		Player savedPlayerData = this.entityManager.persistAndFlush(testPlayer);
		assertThat(savedPlayerData.getName().equals("testName"));
	}
	
	@Test
	public void createPlayerNullName_ThrowsIllegalArgumentException() {
		try {
			testPlayer = new Player("","Aa123456!");
			fail("Exception Expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("El nombre es un campo obligatorio para poder registrarse"));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}
	@Test
	public void createPlayerWrongPasswordFormat_ThrowsIllegalArgumentException() {
		try {
			testPlayer = new Player("test","Aa3456!");//<8char
			testPlayer = new Player("test","Aa123456789123456!");//>15char
			testPlayer = new Player("test","Aaaaaaaa!");//No digit
			testPlayer = new Player("test","aa123456!");//No capital letter
			testPlayer = new Player("test","AA123456!");//No lowercase letter
			testPlayer = new Player("test","Aa123456");//No special char
			fail("Exception Expected!");
		} catch(IllegalArgumentException e) {
			assertThat(IllegalArgumentException.class.equals(e.getClass()));
			assertThat(e.getMessage().equals("\"Contraseña requiere:\\n-Entre 8 y 15 caracteres con al menos un dígito,una mayúscula,una minúscula y un caracter especial.No admite espacios en blanco\""));
		}catch(Exception e) {
			fail("wrong exception thrown");
		}
	}

}


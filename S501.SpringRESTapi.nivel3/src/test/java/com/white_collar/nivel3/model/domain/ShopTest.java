package com.white_collar.nivel3.model.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class ShopTest {

	@Autowired
	private TestEntityManager entityManager;
	
	private static Shop testShop;
	
	@BeforeAll
	public static void setUp() {
		testShop = new Shop("testName",10);
	}
	
	@Test
	final void saveShop() {
		Shop savedShopData = this.entityManager.persistAndFlush(testShop);
		assertThat(savedShopData.getName().equals("testName"));
	}
}

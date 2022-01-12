package com.white_collar.nivel3.model.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.white_collar.nivel3.model.domain.Shop;

@DataJpaTest
@ActiveProfiles("test")
class ShopRepositoryTest {

	@Autowired
	private ShopRepository shopRepository;
	
	private static Shop shop;
	
	@Test
	final void testSaveShop() {
		long count = shopRepository.count();
		
		shopRepository.save(shop);
		long expected = count+1;
		
		assertThat(shopRepository.count()==expected);
	}

	@Test
	final void testSaveShopAndFindById() {
		shop = new Shop("nameTest", 10);
		shopRepository.save(shop);
		assertThat(shopRepository.findById(shop.getId()).get().getName().equals("nameTest"));
	}

}

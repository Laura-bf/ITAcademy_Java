package com.white_collar.nivel3.model.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class PictureTest {

	@Autowired
	private TestEntityManager entityManager;

	@Test
	final void createPicturesNameAnonymous() {
		Picture pict1 = new Picture("pictName","");
		Picture savedPictureData = this.entityManager.persist(pict1);
		assertTrue(savedPictureData.getAuthor().equals("anonymous"));
	}

}

package com.white_collar.nivel3.model.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PictureTest {

	@Test
	void test() {
		Picture picture = new Picture("name","");
		String author = picture.getAuthor();
		assertTrue(author.equalsIgnoreCase("anonymous"));
	}

}

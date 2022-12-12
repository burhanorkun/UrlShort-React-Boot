package com.orkun.shorturl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ShorturlApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void main_SHOULD_loadContext_WHEN_applicationstarts() {
		assertNotNull(this);
	}

}

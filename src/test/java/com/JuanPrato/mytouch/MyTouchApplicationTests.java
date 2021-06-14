package com.JuanPrato.mytouch;

import com.JuanPrato.mytouch.controller.TestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyTouchApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testHello() {
		TestController controller = new TestController();
		String response = controller.hello();

		Assertions.assertEquals("CONNECTED", response);
	}

}

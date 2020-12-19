package br.com.donus.manageaccountapi;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManageAccountApiApplicationTests {

	@Test
	void contextLoads() {
		String[] arr = {"1","2"}; 
		ManageAccountApiApplication main = new ManageAccountApiApplication();
		ManageAccountApiApplication.main(arr);
		Assertions.assertNotNull(main);
	}

}

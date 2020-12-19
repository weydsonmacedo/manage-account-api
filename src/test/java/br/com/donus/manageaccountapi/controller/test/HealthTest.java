package br.com.donus.manageaccountapi.controller.test;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.controller.Health;

@DisplayName("Tests for the Health endpoint ")
@ExtendWith(SpringExtension.class)
class HealthTest {

	@InjectMocks
	private Health controller;
	@Test
	void testHealth() {
		LocalDate lc = controller.health().getBody();
		Assertions.assertNotNull(lc);
	}

}

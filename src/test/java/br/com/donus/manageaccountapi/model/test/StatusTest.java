package br.com.donus.manageaccountapi.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.model.Status;

@DisplayName("Tests for StatusTest  class ")
@ExtendWith(SpringExtension.class)
class StatusTest {

	@Test
	void testGetStatus() {
		Assertions.assertNotNull(Status.ACTIVE.getStatus());
	}

}

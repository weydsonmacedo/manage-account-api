package br.com.donus.manageaccountapi.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.model.TransactionType;

@DisplayName("Tests for TransactionTypeTest  class ")
@ExtendWith(SpringExtension.class)
class TransactionTypeTest {

	@Test
	void testGetMovementType() {
		Assertions.assertNotNull(TransactionType.DEPOSIT.getMovementType());
	}

}

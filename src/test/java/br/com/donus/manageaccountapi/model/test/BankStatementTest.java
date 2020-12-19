package br.com.donus.manageaccountapi.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import util.MockClasses;

@DisplayName("Tests for BankStatementTest  class ")
@ExtendWith(SpringExtension.class)
class BankStatementTest {

	@Test
	void testBankStatement() {
		MockClasses.getBankStatement().getId();
		
		MockClasses.getBankStatement().setId(1L);
		MockClasses.getBankStatement().getCreationDate();
		MockClasses.getBankStatement().toString();
		MockClasses.getBankStatement().hashCode();
		Assertions.assertNotNull(MockClasses.getBankStatement());
		MockClasses.getBankStatement().equals(MockClasses.getBankStatement());
	}

}

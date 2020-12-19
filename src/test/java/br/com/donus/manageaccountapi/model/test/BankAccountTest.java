package br.com.donus.manageaccountapi.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import util.MockClasses;

@DisplayName("Tests for BankAccountTest  class ")
@ExtendWith(SpringExtension.class)
class BankAccountTest {

	@Test
	void testBankAccount() {
		MockClasses.getBankAccount().getModifiedDate();
		MockClasses.getBankAccount().setId(1L);
		MockClasses.getBankAccount().toString();
		MockClasses.getBankAccount().hashCode();
		Assertions.assertNotNull(MockClasses.getBankAccount());
		MockClasses.getBankAccount().equals(MockClasses.getBankAccount());
	}

}

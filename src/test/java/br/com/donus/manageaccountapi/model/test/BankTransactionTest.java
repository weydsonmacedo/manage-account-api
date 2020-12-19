package br.com.donus.manageaccountapi.model.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.model.BankTransaction;
import util.MockClasses;

@DisplayName("Tests for BankTransactionTest  class ")
@ExtendWith(SpringExtension.class)
class BankTransactionTest {

	@Test
	void testBankTransaction() {
		MockClasses.getBankTransaction().getId();
		MockClasses.getBankTransaction().setId(1L);
		MockClasses.getBankTransaction().getCreationDate();
		MockClasses.getBankTransaction().setTransactionCode(java.util.UUID.randomUUID());
		MockClasses.getBankTransaction().getTransactionCode();
		BankTransaction codeNull =	MockClasses.getBankTransaction();
		codeNull.setTransactionCode(null);
		codeNull.getTransactionCode();
		MockClasses.getBankTransaction().toString();
		MockClasses.getBankTransaction().hashCode();
		Assertions.assertNotNull(MockClasses.getBankTransaction());
		MockClasses.getBankTransaction().equals(MockClasses.getBankTransaction());
	}

}

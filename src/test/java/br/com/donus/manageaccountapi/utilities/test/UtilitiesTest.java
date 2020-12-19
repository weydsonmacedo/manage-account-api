package br.com.donus.manageaccountapi.utilities.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.model.BankAccount;

@DisplayName("Tests for the utilities class ")
@ExtendWith(SpringExtension.class)
class UtilitiesTest {

	

	
	@Test
	void testValidateBalance() {
		BigDecimal zero = Utilities.validateBalance(new BankAccount());
		Assertions.assertEquals(BigDecimal.ZERO, zero);
		
		new Utilities();
	}
	
	@Test
	void testInstance() {
		Assertions.assertNotNull(new Utilities());
		
	}

}

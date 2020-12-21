package br.com.donus.manageaccountapi.utilities.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
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
	
	@Test
	void parseListToBankStatementDTOTestEmptyList() {
		
		BussinessException bussinesException = Assertions.assertThrows(BussinessException.class, () -> Utilities.parseListToBankStatementDTO(List.of()));
		
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, bussinesException.getStatus());
		Assertions.assertEquals("OCORREU UM ERRO! LISTA DE EXTRATO VAZIA", bussinesException.getMessage());
	}

}

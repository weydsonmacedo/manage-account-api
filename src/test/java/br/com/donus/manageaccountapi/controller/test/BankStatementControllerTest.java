package br.com.donus.manageaccountapi.controller.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.controller.BankStatementController;
import br.com.donus.manageaccountapi.dto.response.BankStatementDTO;
import br.com.donus.manageaccountapi.service.BankStatementService;
import util.MockClasses;

@DisplayName("Tests for the bank statement Controller ")
@ExtendWith(SpringExtension.class)
class BankStatementControllerTest {

	@InjectMocks
	private BankStatementController controller;
	
	@Mock
	private BankStatementService bankStatementServiceMock;
	
	@BeforeEach
	private void precondition() {
		BDDMockito.when(bankStatementServiceMock.getStatement(MockClasses.getBankAccTestWithId().getCpf()))
		.thenReturn(MockClasses.getBankStatementDTO());
	}

	@Test
	void testStatement() {
		BankStatementDTO dto =	controller.statement(MockClasses.getBankAccTestWithId().getCpf()).getBody();
		Assertions.assertNotNull(dto);
	}

}

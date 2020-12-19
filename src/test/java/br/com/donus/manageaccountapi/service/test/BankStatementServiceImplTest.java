package br.com.donus.manageaccountapi.service.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.dto.response.BankStatementDTO;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankStatement;
import br.com.donus.manageaccountapi.repository.BankStatementRepository;
import br.com.donus.manageaccountapi.service.impl.BankAccountServiceImpl;
import br.com.donus.manageaccountapi.service.impl.BankStatementServiceImpl;
import util.MockClasses;

@DisplayName("Tests for the bank statement service impl ")
@ExtendWith(SpringExtension.class)
class BankStatementServiceImplTest {
	
	@InjectMocks
	BankStatementServiceImpl service;

	@Mock
	BankStatementRepository bankStatementRepositoryMock;
	
	@Mock
	BankAccountServiceImpl baccMock;
	

	@BeforeEach
	private void precondition() {
		BDDMockito.when(bankStatementRepositoryMock.save(ArgumentMatchers.any(BankStatement.class)))
		.thenReturn(MockClasses.getBankStatement());
		
		BDDMockito.when(bankStatementRepositoryMock.findByBankAccount(ArgumentMatchers.any(BankAccount.class)))
		.thenReturn(MockClasses.getListBankStatement());
		
		BDDMockito.when(baccMock.findByCpf(ArgumentMatchers.any(String.class)))
		.thenReturn(MockClasses.getBankAccount());
	}
	
	
	@Test
	void testGenerateBankStatement() {
		BankStatement bankSt = service.generateBankStatement(MockClasses.getBankTransaction(), MockClasses.getBankAccTestWithId(), MockClasses.getBankAccTestWithId().getBalance(), BigDecimal.TEN);
		Assertions.assertNotNull(bankSt);
	}

	@Test
	void testGetStatementSucessful() {
		BankStatementDTO statement = service.getStatement(MockClasses.getBankAccountDTOTest().getCpf());
		Assertions.assertNotNull(statement);
		
	}
	
	@Test
	void testGetStatementBussinesException() {
		BDDMockito.when(bankStatementRepositoryMock.findByBankAccount(ArgumentMatchers.any()))
		.thenReturn(List.of());
		Assertions.assertThrows(BussinessException.class, () -> service.getStatement(MockClasses.getBankAccountDTOTest().getCpf()));
		
	}

}

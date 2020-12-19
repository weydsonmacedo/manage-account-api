package br.com.donus.manageaccountapi.service.test;

import java.math.BigDecimal;

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

import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.model.TransactionType;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.BankStatementService;
import br.com.donus.manageaccountapi.service.BankTransactionService;
import br.com.donus.manageaccountapi.service.impl.DepositServiceImpl;
import util.MockClasses;

@DisplayName("Tests for the bank deposit service impl ")
@ExtendWith(SpringExtension.class)
class DepositServiceImplTest {

	@InjectMocks
	private DepositServiceImpl service;
	
	@Mock
	private BankAccountService baccServiceMock;
	
	@Mock
	private BankTransactionService bankTransactionServiceMock;
	
	@Mock
	private BankStatementService  bankStatementServiceMock;
	
	@BeforeEach
	private void precondition() {
		BDDMockito.when(baccServiceMock.findByCpf(ArgumentMatchers.any(String.class)))
				.thenReturn(MockClasses.getBankAccount());

		BDDMockito.when(baccServiceMock.update(ArgumentMatchers.any(BankAccount.class)))
		.thenReturn(MockClasses.getBankAccount(),MockClasses.getBankAccTestWithId());

		BDDMockito.when(bankTransactionServiceMock.transact(ArgumentMatchers.any(BankAccount.class), 
				ArgumentMatchers.any(TransactionType.class),
				ArgumentMatchers.any(BigDecimal.class), ArgumentMatchers.any(BigDecimal.class),
				ArgumentMatchers.any(BigDecimal.class))).thenReturn(MockClasses.getBankTransaction());

		BDDMockito.when(bankStatementServiceMock.generateBankStatement(ArgumentMatchers.any(BankTransaction.class),
				ArgumentMatchers.eq(MockClasses.getBankAccount()), ArgumentMatchers.eq(BigDecimal.ONE),
				ArgumentMatchers.eq(BigDecimal.ONE))).thenReturn(MockClasses.getBankStatement());
		
	}
	
	@Test
	void testDeposit() {
		
		ResponseTransactionInfoDTO deposit = service.deposit(MockClasses.getDepositDTO());
		Assertions.assertNotNull(deposit);
		
	}

}

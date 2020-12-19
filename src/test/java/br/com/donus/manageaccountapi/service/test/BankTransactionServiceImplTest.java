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

import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.model.TransactionType;
import br.com.donus.manageaccountapi.repository.BankTransactionRepository;
import br.com.donus.manageaccountapi.service.impl.BankTransactionServiceImpl;
import util.MockClasses;

@DisplayName("Tests for the bank transaction service impl ")
@ExtendWith(SpringExtension.class)
class BankTransactionServiceImplTest {

	@InjectMocks
	BankTransactionServiceImpl service;
	
	@Mock
	private BankTransactionRepository bankTransactionRepositoryMock;
	

	@BeforeEach
	private void precondition() {
		BDDMockito.when(bankTransactionRepositoryMock.save(ArgumentMatchers.any(BankTransaction.class)))
		.thenReturn(MockClasses.getBankTransaction());
	}
	
	
	@Test
	void testTransactAllArguments() {
		BankTransaction transact = service.transact(MockClasses.getBankAccTestWithId(), MockClasses.getBankAccTestWithId2(),
				TransactionType.TRANSFER, BigDecimal.TEN,  BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertNotNull(transact);
	}

	@Test
	void testTransactSameArguments() {
		BankTransaction transact = service.transact(MockClasses.getBankAccTestWithId2(),
				TransactionType.TRANSFER, BigDecimal.TEN,  BigDecimal.ZERO, BigDecimal.ZERO);
		Assertions.assertNotNull(transact);
	}

}

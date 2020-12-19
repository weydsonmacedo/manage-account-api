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
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.model.TransactionType;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.BankStatementService;
import br.com.donus.manageaccountapi.service.BankTransactionService;
import br.com.donus.manageaccountapi.service.impl.WithdrawServiceImpl;
import util.MockClasses;

@DisplayName("Tests for the bank withdraw service impl ")
@ExtendWith(SpringExtension.class)
class WithdrawServiceImplTest {

	@InjectMocks
	WithdrawServiceImpl service;
	
	@Mock
	private BankAccountService baccServiceMock;
	
	@Mock
	private BankTransactionService bankTransactionServiceMock;
	
	@Mock
	private BankStatementService  bankStatementServiceMock;
	
	@BeforeEach
	private void precondition() {
		BDDMockito.when(baccServiceMock.findByCpf(ArgumentMatchers.any(String.class)))
				.thenReturn(MockClasses.getBankAccountTENOfBalance());

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
	void testDraw() {
		ResponseTransactionInfoDTO draw = service.draw(MockClasses.getWithdrawDTOOne());
		Assertions.assertNotNull(draw);
	}
	
	@Test
	void testDrawInsuficientBalanceBussinessException() {
		String msg = Assertions.assertThrows(BussinessException.class, () -> service.draw(MockClasses.getWithdrawDTOHUNDRED())).getMessage();
		Assertions.assertEquals("SEU SALDO É INSUFICIENTE PARA REALIZAR ESTA OPERAÇÃO. SALDO ATUAL: ".concat(BigDecimal.TEN.toString()), msg);
	}
	
	@Test
	void testDrawInsuficientBalanceFeeBussinessException() {
		String msg = Assertions.assertThrows(BussinessException.class, () -> service.draw(MockClasses.getWithdrawDTO())).getMessage();
		Assertions.assertEquals("SALDO INSUFICIENTE PARA REALIZAR ESTA OPERAÇÃO. ATENTE-SE PARA A TAXA DE 1%. SALDO ATUAL: ".concat(BigDecimal.TEN.toString()), msg);
	}

}

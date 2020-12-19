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

import br.com.donus.manageaccountapi.dto.response.TransferResponseDTO;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.model.TransactionType;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.BankStatementService;
import br.com.donus.manageaccountapi.service.BankTransactionService;
import br.com.donus.manageaccountapi.service.impl.BankTransferServiceImpl;
import util.MockClasses;

@DisplayName("Tests for the bank transfer service impl ")
@ExtendWith(SpringExtension.class)
class BankTransferServiceImplTest {

	@InjectMocks
	private BankTransferServiceImpl service;
	
	@Mock
	private BankAccountService bankAccountServiceMock;
	
	@Mock
	private BankTransactionService bankTransactionService;
	
	@Mock
	private BankStatementService  bankStatementServiceMock;
	
	@BeforeEach
	private void precondition() {
		BDDMockito.when(bankAccountServiceMock.findByCpf(ArgumentMatchers.any(String.class)))
				.thenReturn(MockClasses.getBankAccount(),MockClasses.getBankAccTestWithId());

		BDDMockito.when(bankStatementServiceMock.generateBankStatement(ArgumentMatchers.any(BankTransaction.class),
				ArgumentMatchers.eq(MockClasses.getBankAccount()), ArgumentMatchers.eq(BigDecimal.ONE),
				ArgumentMatchers.eq(BigDecimal.ONE))).thenReturn(MockClasses.getBankStatement());

		BDDMockito.when(bankTransactionService.transact(ArgumentMatchers.any(BankAccount.class),
				ArgumentMatchers.any(BankAccount.class), ArgumentMatchers.any(TransactionType.class),
				ArgumentMatchers.any(BigDecimal.class), ArgumentMatchers.any(BigDecimal.class),
				ArgumentMatchers.any(BigDecimal.class))).thenReturn(MockClasses.getBankTransaction());
		
		BDDMockito.when(bankAccountServiceMock.update(ArgumentMatchers.any(BankAccount.class)))
		.thenReturn(MockClasses.getBankAccount(),MockClasses.getBankAccTestWithId());
	}
	
	
	
	@Test
	void testTransfer() {
		TransferResponseDTO transfer = service.transfer(MockClasses.getBankTransferDTO());
		Assertions.assertNotNull(transfer);
	}
	
	@Test
	void testTransferSameAccountBussinessException() {
		BussinessException assertThrows = Assertions.assertThrows(BussinessException.class, () -> service.transfer(MockClasses.getBankTransferDTOSameCPF()));
		Assertions.assertEquals("CONTAS IGUAIS! NÃO É PERMITIDO A TRANSFERÊNCIA PARA A MESMA CONTA. CPF: ".concat(MockClasses.getBankTransferDTOSameCPF().getCpfReceiver()),
				assertThrows.getMessage());
	}
	
	
	@Test
	void testTransferValidateBalanceBussinessException() {
		BussinessException assertThrows = Assertions.assertThrows(BussinessException.class, () -> service.transfer(MockClasses.getBankTransferDTOValueTen()));
		Assertions.assertEquals("SEU SALDO É INSUFICIENTE PARA REALIZAR ESTA OPERAÇÃO. SALDO ATUAL: ".concat(MockClasses.getBankAccount().getBalance().toString()), assertThrows.getMessage());
	}

}

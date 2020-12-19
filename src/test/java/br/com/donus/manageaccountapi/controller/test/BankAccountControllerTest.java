package br.com.donus.manageaccountapi.controller.test;


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

import br.com.donus.manageaccountapi.controller.BankAccountController;
import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.request.DepositDTO;
import br.com.donus.manageaccountapi.dto.request.WithdrawDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.DepositService;
import br.com.donus.manageaccountapi.service.WithdrawService;
import util.MockClasses;

@DisplayName("Tests for the bank account Controller ")
@ExtendWith(SpringExtension.class)
public class BankAccountControllerTest {

	@InjectMocks
	private BankAccountController controller;
	
	
	@Mock
	private BankAccountService bankAccountServiceMock;
	@Mock
	private DepositService depositServiceMock;
	@Mock
	private WithdrawService drawServiceMock;
	
	@BeforeEach
	void precondition() {
		successCase();		
	}


	private void successCase() {
		BDDMockito.when(bankAccountServiceMock.create(ArgumentMatchers.any(BankAccountDTO.class)))
		.thenReturn(MockClasses.getBankAccountInfoDTO());
		
		BDDMockito.when(depositServiceMock.deposit(ArgumentMatchers.any(DepositDTO.class)))
		.thenReturn(MockClasses.getResponseTransactionInfoDTO());
		
		BDDMockito.when(drawServiceMock.draw(ArgumentMatchers.any(WithdrawDTO.class)))
		.thenReturn(MockClasses.getResponseTransactionInfoDTO());
	}
	
	
	@Test
	public void testCreateSucessful() {
		
		BankAccountInfoDTO dto = controller.create(MockClasses.getBankAccountDTOTest()).getBody();
		Assertions.assertNotNull(dto);
	}
		
	@Test
	public void testDepositSucessful() {
		
		ResponseTransactionInfoDTO dto = controller.deposit(MockClasses.getDepositDTO()).getBody();
		Assertions.assertNotNull(dto);
	}
	
	@Test
	public void testWithdrawSucessful() {
		
		ResponseTransactionInfoDTO dto = controller.withdraw(MockClasses.getWithdrawDTO()).getBody();
		Assertions.assertNotNull(dto);
	}
}

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
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.DepositService;
import br.com.donus.manageaccountapi.service.WithdrawService;
import util.MockClasses;

@DisplayName("Tests for the BankAccountController ")
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
		BDDMockito.when(bankAccountServiceMock.create(ArgumentMatchers.any(BankAccountDTO.class)))
		.thenReturn(MockClasses.getBankAccountInfoDTO());	
		
		BDDMockito.when(bankAccountServiceMock.delete(ArgumentMatchers.any(String.class)))
		.thenReturn("");
	}

	
	@Test
	public void testCreateSucessful() {
		
		BankAccountInfoDTO dto = controller.create(MockClasses.getBankAccountDTOTest()).getBody();
		Assertions.assertNotNull(dto);
	}
		

	@Test
	public void testDeleteSucessful() {
		
		String msg = controller.delete(MockClasses.getBankAccountDTOTest().getCpf()).getBody();
		Assertions.assertNotNull(msg);
	}
		
}

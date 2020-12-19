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

import br.com.donus.manageaccountapi.controller.BankTransferController;
import br.com.donus.manageaccountapi.dto.request.BankTransferDTO;
import br.com.donus.manageaccountapi.dto.response.TransferResponseDTO;
import br.com.donus.manageaccountapi.service.BankTransferService;
import util.MockClasses;
@DisplayName("Tests for the bank transfer Controller ")
@ExtendWith(SpringExtension.class)
class BankTransferControllerTest {

	@InjectMocks
	BankTransferController controller;
	
	@Mock
	private BankTransferService bankTransferServiceMock;
	
	
	@BeforeEach
	private void precondition() {
		BDDMockito.when(bankTransferServiceMock.transfer(ArgumentMatchers.any(BankTransferDTO.class)))
		.thenReturn(MockClasses.getTransferResponseDTO());
	}
	
	
	@Test
	void testTransfer() {
		TransferResponseDTO dto = controller.transfer(MockClasses.getBankTransferDTO()).getBody();
		Assertions.assertNotNull(dto);
	}

}

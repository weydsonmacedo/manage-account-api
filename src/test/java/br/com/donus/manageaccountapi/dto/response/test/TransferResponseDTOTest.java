package br.com.donus.manageaccountapi.dto.response.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.response.TransferResponseDTO;


@DisplayName("Tests for TransferResponseDTOTest  class ")
@ExtendWith(SpringExtension.class)
class TransferResponseDTOTest {

	@Test
	void test() {

		TransferResponseDTO dto = new TransferResponseDTO();
		dto.setBankAccountDonor( new BankAccountDTO());
		dto.setBankAccountReceiver( new BankAccountDTO());
		dto.setTransactionCode("7cd7acc8-a777-493e-aa82-bb65cf21336b");
		dto.setTransferedValue(BigDecimal.ONE);
		TransferResponseDTO dto2 = new TransferResponseDTO(dto.getTransactionCode(), dto.getBankAccountDonor(),
				dto.getBankAccountReceiver(), dto.getTransferedValue());
		Assertions.assertNotNull(dto.toString());
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto2);
	}


}

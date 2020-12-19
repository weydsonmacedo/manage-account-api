package br.com.donus.manageaccountapi.dto.response.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;

@DisplayName("Tests for ResponseTransactionInfoDTOTest  class ")
@ExtendWith(SpringExtension.class)
class ResponseTransactionInfoDTOTest {

	@Test
	void test() {
		
		ResponseTransactionInfoDTO dto = new ResponseTransactionInfoDTO();
		ResponseTransactionInfoDTO dto2 = new ResponseTransactionInfoDTO(dto.getTransactionCode());
		dto2.setBalance(dto.getBalance());
		dto2.setBankAccCreationDate(dto.getBankAccCreationDate());
		dto2.setCpf(dto.getCpf());
		dto2.setName(dto.getName());
		dto2.setTransactionCode(dto.getTransactionCode());
		
		dto.setTransactionCode("7cd7acc8-a777-493e-aa82-bb65cf21336b");
		Assertions.assertNotNull(dto.toString());
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto2);
	}

}

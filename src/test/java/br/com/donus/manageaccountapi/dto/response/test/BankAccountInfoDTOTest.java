package br.com.donus.manageaccountapi.dto.response.test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;

@DisplayName("Tests for BankAccountInfoDTOTest  class ")
@ExtendWith(SpringExtension.class)
class BankAccountInfoDTOTest {

	@Test
	void test() {
		
		BankAccountInfoDTO dto = new BankAccountInfoDTO();
		dto.setCpf("05678956412");
		dto.setName("teste");
		dto.setBankAccCreationDate(LocalDateTime.now());
		dto.setBalance(BigDecimal.ONE);
		BankAccountInfoDTO dto2 = new BankAccountInfoDTO(dto.getCpf(), dto.getName(), dto.getBalance(), dto.getBankAccCreationDate());
		Assertions.assertNotNull(dto.toString());
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto2);
	}

}

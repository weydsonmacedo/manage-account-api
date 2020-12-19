package br.com.donus.manageaccountapi.dto.request.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.dto.request.BankTransferDTO;

@DisplayName("Tests for BankAccountDTO class ")
@ExtendWith(SpringExtension.class)
class BankTransferDTOTest {

	@Test
	void test() {
		BankTransferDTO dto = new BankTransferDTO();
		dto.setCpfDonor("05760872594");
		dto.setCpfReceiver("05760874595");
		dto.setValue(BigDecimal.TEN);
		Assertions.assertNotNull(dto.toString());
		Assertions.assertNotNull(dto);
	}

}

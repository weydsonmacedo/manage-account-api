package br.com.donus.manageaccountapi.dto.request.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.dto.request.DepositDTO;


@DisplayName("Tests for DepositDTOTest class ")
@ExtendWith(SpringExtension.class)
class DepositDTOTest {

	@Test
	void test() {
		DepositDTO dto = new DepositDTO();
		dto.setCpfToDeposit("05720874594");
		dto.setValue(BigDecimal.TEN);
		Assertions.assertNotNull(dto.toString());
		Assertions.assertNotNull(dto);
	}

}

package br.com.donus.manageaccountapi.dto.request.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.dto.request.WithdrawDTO;

@DisplayName("Tests for WithdrawDTOTest  class ")
@ExtendWith(SpringExtension.class)
class WithdrawDTOTest {

	@Test
	void test() {
		WithdrawDTO dto = new WithdrawDTO();
		dto.setCpfWithdraw("05760874194");
		dto.setValue(BigDecimal.TEN);
		Assertions.assertNotNull(dto.toString());
		Assertions.assertNotNull(dto);
	}

}

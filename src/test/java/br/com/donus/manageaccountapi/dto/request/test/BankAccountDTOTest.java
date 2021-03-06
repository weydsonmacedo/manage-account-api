package br.com.donus.manageaccountapi.dto.request.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;

@DisplayName("Tests for BankAccountDTO class ")
@ExtendWith(SpringExtension.class)
class BankAccountDTOTest {

	@Test
	void test() {

		BankAccountDTO dto = new BankAccountDTO();
		dto.setCpf("05760374594");
		dto.setName("teste");
		Assertions.assertNotNull(dto.toString());
		Assertions.assertNotNull(dto);

	}
}

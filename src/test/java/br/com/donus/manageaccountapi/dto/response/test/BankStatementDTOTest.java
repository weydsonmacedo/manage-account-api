package br.com.donus.manageaccountapi.dto.response.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.dto.response.BankStatementDTO;
import br.com.donus.manageaccountapi.dto.response.StatementDTO;

@DisplayName("Tests for BankStatementDTOTest  class ")
@ExtendWith(SpringExtension.class)
class BankStatementDTOTest {

	@Test
	void test() {
		
		BankStatementDTO dto = new BankStatementDTO();
		dto.setBankAccount( new BankAccountInfoDTO());
		dto.setBankStatement(List.of( new StatementDTO()));
		BankStatementDTO dto2 = new BankStatementDTO(dto.getBankAccount(),dto.getBankStatement());
		Assertions.assertNotNull(dto.toString());
		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto2);
	}

}

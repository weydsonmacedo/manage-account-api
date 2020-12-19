package br.com.donus.manageaccountapi.dto.response.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.dto.response.StatementDTO;
import util.MockClasses;

@DisplayName("Tests for StatementDTOTest  class ")
@ExtendWith(SpringExtension.class)
class StatementDTOTest {

	@Test
	void test() {
		
		StatementDTO dto = new StatementDTO();
		dto.setBonification(MockClasses.getStatementDTO().getBonification());
		dto.setCpfDonor(MockClasses.getStatementDTO().getCpfDonor());
		dto.setCpfReceiver(MockClasses.getStatementDTO().getCpfReceiver());
		dto.setCurrentBalance(MockClasses.getStatementDTO().getCurrentBalance());
		dto.setFee(MockClasses.getStatementDTO().getFee());
		dto.setNameDonor(MockClasses.getStatementDTO().getNameDonor());
		dto.setNameReceiver(MockClasses.getStatementDTO().getNameReceiver());
		dto.setOperationDate(MockClasses.getStatementDTO().getOperationDate());
		dto.setPreviousBalance(MockClasses.getStatementDTO().getPreviousBalance());
		dto.setTransactionCode(MockClasses.getStatementDTO().getTransactionCode());
		dto.setTransactionType(MockClasses.getStatementDTO().getTransactionType());
		dto.setValueTransaction(MockClasses.getStatementDTO().getValueTransaction());
		
		Assertions.assertNotNull(dto.toString());
		Assertions.assertNotNull(dto);
	}

}

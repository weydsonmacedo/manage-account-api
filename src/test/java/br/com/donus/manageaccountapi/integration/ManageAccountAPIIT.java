package br.com.donus.manageaccountapi.integration;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.request.BankTransferDTO;
import br.com.donus.manageaccountapi.dto.request.DepositDTO;
import br.com.donus.manageaccountapi.dto.request.WithdrawDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.dto.response.BankStatementDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;
import br.com.donus.manageaccountapi.dto.response.TransferResponseDTO;
import util.MockClasses;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DisplayName("class ManageAccountAPIIT for integration test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ManageAccountAPIIT {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private ConfigurationsIT configurationsIT;
	
	@Test
	@Order(1)
	@DisplayName("Endpoint to create a bank account - > BankAccountController")
	 void create() {

		 ResponseEntity<BankAccountInfoDTO> exchange = testRestTemplate.exchange("/bank-account/create", HttpMethod.POST, 
				new HttpEntity<BankAccountDTO>(MockClasses.getBankAccountDTOTest(),configurationsIT.createHeadersWithAuthentication()),
				BankAccountInfoDTO.class);
		 BankAccountInfoDTO body = exchange.getBody();
		 
		Assertions.assertNotNull(body.getName());
		Assertions.assertNotNull(body.getCpf());
		Assertions.assertNotNull(body.getBalance());
	}
	
	@Test
	@Order(2)
	@DisplayName("Endpoint to deposit in a bank account -> BankAccountOperationController")
	 void deposit() {

		 ResponseEntity<ResponseTransactionInfoDTO> exchange = testRestTemplate.exchange("/account-operation/deposit", HttpMethod.POST, 
				new HttpEntity<DepositDTO>(MockClasses.getDepositDTO(),configurationsIT.createHeadersWithAuthentication()),
				ResponseTransactionInfoDTO.class);
		 ResponseTransactionInfoDTO body = exchange.getBody();
		 
			Assertions.assertNotNull(body.getTransactionCode());
			Assertions.assertNotNull(body.getName());
			Assertions.assertNotNull(body.getCpf());
			Assertions.assertNotNull(body.getBalance());
			Assertions.assertNotNull(body.getBankAccCreationDate());
			Assertions.assertNotNull(body.getAccountStatus());
	}
	
	@Test
	@Order(3)
	@DisplayName("Endpoint to withdraw in a bank account -> BankAccountOperationController")
	 void withdraw() {

		 ResponseEntity<ResponseTransactionInfoDTO> exchange = testRestTemplate.exchange("/account-operation/withdraw", HttpMethod.POST, 
				new HttpEntity<WithdrawDTO>(MockClasses.getWithdrawDTOOne(),configurationsIT.createHeadersWithAuthentication()),
				ResponseTransactionInfoDTO.class);
		 ResponseTransactionInfoDTO body = exchange.getBody();
		 
		Assertions.assertNotNull(body.getTransactionCode());
		Assertions.assertNotNull(body.getName());
		Assertions.assertNotNull(body.getCpf());
		Assertions.assertNotNull(body.getBalance());
		Assertions.assertNotNull(body.getBankAccCreationDate());
		Assertions.assertNotNull(body.getAccountStatus());
	}
	
	@Test
	@Order(4)
	@DisplayName("Endpoint to create a bank account 2 - > BankAccountController")
	 void createOther() {

		 ResponseEntity<BankAccountInfoDTO> exchange = testRestTemplate.exchange("/bank-account/create", HttpMethod.POST, 
				new HttpEntity<BankAccountDTO>(MockClasses.getBankAccountDTOTest2(),configurationsIT.createHeadersWithAuthentication()),
				BankAccountInfoDTO.class);
		 BankAccountInfoDTO body = exchange.getBody();
		 
		Assertions.assertNotNull(body.getName());
		Assertions.assertNotNull(body.getCpf());
		Assertions.assertNotNull(body.getBalance());
	}
	
	@Test
	@Order(5)
	@DisplayName("Endpoint to transfer from first account to second account -> BankTransferController")
	 void transfer() {

		 ResponseEntity<TransferResponseDTO> exchange = testRestTemplate.exchange("/bank-transfer/transfer", HttpMethod.POST, 
				new HttpEntity<BankTransferDTO>(MockClasses.getBankTransferDTO(),configurationsIT.createHeadersWithAuthentication()),
				TransferResponseDTO.class);
		 TransferResponseDTO body = exchange.getBody();
		 
		Assertions.assertNotNull(body.getTransactionCode());
		Assertions.assertNotNull(body.getBankAccountDonor().getCpf());
		Assertions.assertNotNull(body.getBankAccountReceiver().getCpf());
		Assertions.assertNotNull(body.getTransferedValue());
		
		Assertions.assertEquals(body.getTransferedValue(),MockClasses.getBankTransferDTO().getValue());
		Assertions.assertEquals(body.getBankAccountDonor().getCpf(),MockClasses.getBankTransferDTO().getCpfDonor());
		Assertions.assertEquals(body.getBankAccountReceiver().getCpf(),MockClasses.getBankTransferDTO().getCpfReceiver());
	}
	
	
	@Test
	@Order(6)
	@DisplayName("Endpoint to get the statements of operations - > BankStatementController")
	 void statement() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("cpf", MockClasses.getBankAccountDTOTest().getCpf());

		 ResponseEntity<BankStatementDTO> exchange = testRestTemplate.exchange("/bank-statement/statement/{cpf}", HttpMethod.GET, 
				 new HttpEntity<>(configurationsIT.createHeadersWithAuthentication()),BankStatementDTO.class,
				 MockClasses.getBankAccountDTOTest().getCpf());
		 BankStatementDTO body = exchange.getBody();
		  Assertions.assertNotNull(body);
		  Assertions.assertNotNull(body.getBankAccount());
		  Assertions.assertNotNull(body.getBankStatement());
		  Assertions.assertNotNull(body.getBankAccount().getCpf());
		  Assertions.assertNotNull(body.getBankStatement().get(0).getTransactionCode());
		  Assertions.assertEquals(body.getBankAccount().getCpf(),MockClasses.getBankAccountDTOTest().getCpf());
		  
	}
	
	@Test
	@Order(7)
	@DisplayName("Endpoint to delete the first account - > BankAccountController")
	 void delete() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("cpf", MockClasses.getBankAccountDTOTest().getCpf());

		 ResponseEntity<String> exchange = testRestTemplate.exchange("/bank-account/delete/{cpf}", HttpMethod.DELETE, 
				 new HttpEntity<>(configurationsIT.createHeadersWithAuthentication()),String.class,
				 MockClasses.getBankAccountDTOTest().getCpf());
		 String body = exchange.getBody();
		  Assertions.assertNotNull(body);
		  Assertions.assertEquals(body,"CONTA BANCÁRIA DELETADA COM SUCESSO");
		  
	}
}

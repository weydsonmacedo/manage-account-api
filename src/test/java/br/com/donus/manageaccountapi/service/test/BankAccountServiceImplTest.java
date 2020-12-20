package br.com.donus.manageaccountapi.service.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.Status;
import br.com.donus.manageaccountapi.repository.BankAccountRepository;
import br.com.donus.manageaccountapi.service.impl.BankAccountServiceImpl;
import util.MockClasses;
@DisplayName("Tests for the bank account service impl ")
@ExtendWith(SpringExtension.class)
class BankAccountServiceImplTest {

	
	@InjectMocks
	private BankAccountServiceImpl service;
	
	@Mock
	private BankAccountRepository baccRepositoryMock;
	
	
	@BeforeEach
	private void precondition() {
		BDDMockito.when(baccRepositoryMock.save(ArgumentMatchers.any(BankAccount.class)))
		.thenReturn(MockClasses.getBankAccTestWithId());
		
		BDDMockito.when(baccRepositoryMock.findByCpf(ArgumentMatchers.any(String.class)))
		.thenReturn(MockClasses.getBankAccount());

		BDDMockito.when(baccRepositoryMock.findByCpfAndStatus(ArgumentMatchers.any(String.class),ArgumentMatchers.any(Status.class)))
		.thenReturn(MockClasses.getBankAccount());
	}
	
	@Test
	void testCreate() {
		BDDMockito.when(baccRepositoryMock.findByCpf(ArgumentMatchers.any(String.class)))
		.thenReturn(null);
		BankAccountInfoDTO dto = service.create(MockClasses.getBankAccountDTOTest());
		Assertions.assertNotNull(dto);
		Assertions.assertEquals(MockClasses.getBankAccTestWithOutId().getCpf(), dto.getCpf());
	}
	
	@Test
	void testDelete() {
		String msg = service.delete(MockClasses.getBankAccountDTOTest().getCpf());
		Assertions.assertEquals(msg, "CONTA BANCÁRIA DELETADA COM SUCESSO");
	}
	
	@Test
	void testCreateBussinessExceptionAccounAlreadyExists() {
		String msg = Assertions.assertThrows(BussinessException.class, () -> service.create(MockClasses.getBankAccountDTOTest())).getMessage();			
		Assertions.assertEquals("ESTA CONTA JÁ SE ENCONTRA NA BASE DE DADOS!", msg);
	}
	
	@Test
	void testReactivateAccount() {
		BDDMockito.when(baccRepositoryMock.findByCpf(ArgumentMatchers.any(String.class)))
		.thenReturn(MockClasses.getBankAccountINACTIVE());
		BankAccountInfoDTO dto = service.create(MockClasses.getBankAccountDTOTest());
		Assertions.assertNotNull(dto);
		Assertions.assertEquals(MockClasses.getBankAccountINACTIVE().getCpf(), dto.getCpf());
	}

	@Test
	void testUpdate() {
		BankAccount dto = service.update(MockClasses.getBankAccTestWithId());
		Assertions.assertNotNull(dto);
	}
	
	@Test
	void testFindByCpf() {
		BankAccount dto = service.findByCpf(MockClasses.getBankAccTestWithId().getCpf());
		Assertions.assertNotNull(dto);
	}
	
	@Test
	void testFindByCpfResourceNotFoundException() {
		BDDMockito.when(baccRepositoryMock.findByCpfAndStatus(ArgumentMatchers.any(String.class),ArgumentMatchers.any(Status.class)))
		.thenReturn(null);
		Assertions.assertNull(baccRepositoryMock.findByCpfAndStatus("",Status.ACTIVE));
		String msg = Assertions.assertThrows(ResourceNotFoundException.class, () -> service.findByCpf(MockClasses.getBankAccountDTOTest().getCpf())).getMessage();			
		Assertions.assertEquals("O CPF DE NÚMERO: 75531547099 NÃO EXISTE NA BASE DE DADOS", msg);
		
	}

}

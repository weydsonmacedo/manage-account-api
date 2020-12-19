package br.com.donus.manageaccountapi.repository.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.repository.BankAccountRepository;
import util.MockClasses;

@DataJpaTest
@DisplayName("Tests for the bank account Repository")
class BankAccountRepositoryTest {

	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	@Test
	void save_whenSuccessful() {
		BankAccount bacc = MockClasses.getBankAccTestWithOutId();
		BankAccount savedBacc = this.bankAccountRepository.save(bacc);
		Assertions.assertNotNull(savedBacc);
		Assertions.assertNotNull(savedBacc.getId());
	}
	
	@Test
	void testFindByCpf_whenSucessfull() {
		BankAccount bacc = MockClasses.getBankAccTestWithOutId();
		 this.bankAccountRepository.save(bacc);
		BankAccount founddBacc = this.bankAccountRepository.findByCpf(bacc.getCpf());
		Assertions.assertNotNull(founddBacc);
		Assertions.assertEquals(bacc.getCpf(), founddBacc.getCpf());
		
	}
	
	@Test
	void testFindByCpf_whenNull() {
		BankAccount bacc = MockClasses.getBankAccTestWithOutId();
		BankAccount founddBacc = this.bankAccountRepository.findByCpf(bacc.getCpf());
		Assertions.assertNull(founddBacc);
	}
}

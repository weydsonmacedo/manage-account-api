package br.com.donus.manageaccountapi.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.donus.manageaccountapi.model.BankAccount;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long>{

	public BankAccount findByCpf(String cpf);
}

package br.com.donus.manageaccountapi.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.Status;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long>{

	public BankAccount findByCpfAndStatus(String cpf, Status status);
	public BankAccount findByCpf(String cpf);
}

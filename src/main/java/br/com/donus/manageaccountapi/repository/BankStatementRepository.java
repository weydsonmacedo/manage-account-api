package br.com.donus.manageaccountapi.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankStatement;

public interface BankStatementRepository extends PagingAndSortingRepository<BankStatement, Long> {

	public List<BankStatement> findByBankAccount(BankAccount bankAccount);
}

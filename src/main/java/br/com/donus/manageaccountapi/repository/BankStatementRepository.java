package br.com.donus.manageaccountapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.donus.manageaccountapi.model.BankStatement;

public interface BankStatementRepository extends PagingAndSortingRepository<BankStatement, Long> {

}

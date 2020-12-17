package br.com.donus.manageaccountapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.donus.manageaccountapi.model.BankTransaction;

public interface BankTransactionRepository extends PagingAndSortingRepository<BankTransaction, Long> {

}

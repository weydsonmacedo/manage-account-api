package br.com.donus.manageaccountapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.donus.manageaccountapi.model.BankMovement;

public interface BankMovementRepository extends PagingAndSortingRepository<BankMovement, Long> {

}

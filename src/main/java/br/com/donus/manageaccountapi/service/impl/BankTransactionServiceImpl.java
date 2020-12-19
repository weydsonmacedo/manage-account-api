package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.model.TransactionType;
import br.com.donus.manageaccountapi.repository.BankTransactionRepository;
import br.com.donus.manageaccountapi.service.BankTransactionService;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {
	
	private BankTransactionRepository bankTransactionRepository;
	
	public BankTransactionServiceImpl(BankTransactionRepository bankTransactionRepository) {
		this.bankTransactionRepository = bankTransactionRepository;
	}

	@Override
	public BankTransaction transact(BankAccount donor, BankAccount receiver, TransactionType transactionType, BigDecimal value, BigDecimal bonification, BigDecimal fee) {
		BankTransaction bm = new BankTransaction();
		bm.setDonor(donor);
		bm.setTransactionType(transactionType);
		bm.setReceiver(receiver);
		bm.setValue(value);
		bm.setBonification(bonification);
		bm.setFee(fee);
		bm.setCreationDate(LocalDateTime.now());
		return this.save(bm);
	}

	private BankTransaction save(BankTransaction bankTransaction) {
		return bankTransactionRepository.save(bankTransaction);
	}
	
	/**
	 * to use in deposit and withdraw type transactions.
	 */
	@Override
	public BankTransaction transact(BankAccount sameAccount, TransactionType transactionType, BigDecimal value,BigDecimal bonification, BigDecimal fee) {
		return this.transact(sameAccount, sameAccount, transactionType, value, bonification, fee);
	}
	
	

}

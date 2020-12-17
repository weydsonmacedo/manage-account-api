package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.model.TransactionType;
import br.com.donus.manageaccountapi.repository.BankTransactionRepository;
import br.com.donus.manageaccountapi.service.BankTransactionService;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {
	
	@Autowired
	BankTransactionRepository bankTransactionRepository;
	
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

	@Override
	public BankTransaction save(BankTransaction bankTransaction) {
		return bankTransactionRepository.save(bankTransaction);
	}
	
	@Override
	public BankTransaction findById(Long id) {
		 return bankTransactionRepository.findById(id).get();
	}

	/**
	 * to use in deposit and withdraw type transactions.
	 */
	@Override
	public BankTransaction transact(BankAccount sameAccount, TransactionType transactionType, BigDecimal value,BigDecimal bonification, BigDecimal fee) {
		return this.transact(sameAccount, sameAccount, transactionType, value, bonification, fee);
	}
	
	

}

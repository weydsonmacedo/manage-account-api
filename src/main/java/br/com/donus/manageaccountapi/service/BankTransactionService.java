package br.com.donus.manageaccountapi.service;

import java.math.BigDecimal;

import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.model.TransactionType;

public interface BankTransactionService {

	public BankTransaction transact(BankAccount donor, BankAccount receiver, TransactionType transactionType, BigDecimal value, BigDecimal bonification, BigDecimal fee );
	public BankTransaction transact(BankAccount sameAccount, TransactionType transactionType, BigDecimal value, BigDecimal bonification, BigDecimal fee );
}

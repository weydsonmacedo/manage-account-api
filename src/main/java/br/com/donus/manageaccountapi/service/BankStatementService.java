package br.com.donus.manageaccountapi.service;

import java.math.BigDecimal;

import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankMovement;
import br.com.donus.manageaccountapi.model.BankStatement;

public interface BankStatementService {

	public BankStatement generateBankStatement(BankMovement bankMovement, BankAccount bankAccount, BigDecimal previousBalance, BigDecimal currentBalance);
	
	public BankStatement save(BankStatement bankStatement);
}

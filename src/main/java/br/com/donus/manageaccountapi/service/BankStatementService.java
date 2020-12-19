package br.com.donus.manageaccountapi.service;

import java.math.BigDecimal;

import br.com.donus.manageaccountapi.dto.response.BankStatementDTO;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankStatement;
import br.com.donus.manageaccountapi.model.BankTransaction;

public interface BankStatementService {

	public BankStatement generateBankStatement(BankTransaction bankTransaction, BankAccount bankAccount, BigDecimal previousBalance, BigDecimal currentBalance);
	
	public BankStatementDTO getStatement(String cpf); 
}

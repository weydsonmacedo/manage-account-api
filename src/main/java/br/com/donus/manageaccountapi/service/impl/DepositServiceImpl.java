package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.request.DepositDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.model.TransactionType;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.BankStatementService;
import br.com.donus.manageaccountapi.service.BankTransactionService;
import br.com.donus.manageaccountapi.service.DepositService;

@Service
public class DepositServiceImpl implements DepositService {
	
	private BankAccountService baccService;
	
	private BankTransactionService bankTransactionService;
	
	private BankStatementService  bankStatementService;
	
	

	public DepositServiceImpl(BankAccountService baccService, BankTransactionService bankTransactionService,
			BankStatementService bankStatementService) {
		this.baccService = baccService;
		this.bankTransactionService = bankTransactionService;
		this.bankStatementService = bankStatementService;
	}

	@Override
	public ResponseTransactionInfoDTO deposit(DepositDTO dep) {
		BankAccount bacc = baccService.findByCpf(dep.getCpfToDeposit());
		BigDecimal previousBalance = bacc.getBalance();
		doDeposit(bacc, dep);
		bacc = baccService.update(bacc);
		BankTransaction transaction = generateTransactionAndStatement(dep, bacc, previousBalance);
		ResponseTransactionInfoDTO response = Utilities.parseEntityToResponseTransactionInfoDTO(bacc,transaction.getTransactionCode());
		return response;
	}

	private BankTransaction generateTransactionAndStatement(DepositDTO dep, BankAccount bacc,
			BigDecimal previousBalance) {
		BigDecimal bonification = getBonus(dep);
		BankTransaction transaction = bankTransactionService.transact(bacc, TransactionType.DEPOSIT, dep.getValue(),bonification,BigDecimal.ZERO);
		bankStatementService.generateBankStatement(transaction, bacc, previousBalance, bacc.getBalance());
		return transaction;
	}
	
	private void doDeposit(BankAccount cb, DepositDTO dep) {

		cb.setBalance(cb.getBalance().add(dep.getValue()));

		BigDecimal bonus = getBonus(dep);
		
		cb.setBalance(cb.getBalance().add(bonus));

	}
	
	private BigDecimal getBonus(DepositDTO dep) {
		MathContext mc = new MathContext(4);
		BigDecimal bonus = dep.getValue().multiply(new BigDecimal("0.005"), mc);
		return bonus;
	}
	
}

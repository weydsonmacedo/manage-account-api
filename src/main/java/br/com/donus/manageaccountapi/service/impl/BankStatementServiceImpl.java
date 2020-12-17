package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankMovement;
import br.com.donus.manageaccountapi.model.BankStatement;
import br.com.donus.manageaccountapi.repository.BankStatementRepository;
import br.com.donus.manageaccountapi.service.BankStatementService;

@Service
public class BankStatementServiceImpl implements BankStatementService {

	@Autowired
	BankStatementRepository bankStatementRepository;
	
	@Override
	public BankStatement generateBankStatement(BankMovement bankMovement, BankAccount bankAccount,
			BigDecimal previousBalance, BigDecimal currentBalance) {
		BankStatement bs = new BankStatement();
		bs.setBankAccount(bankAccount);
		bs.setBankMovement(bankMovement);
		bs.setPreviousBalance(previousBalance);
		bs.setCurrentBalance(currentBalance);
		bs.setCreationDate(LocalDateTime.now());
		return this.save(bs);
	}

	@Override
	public BankStatement save(BankStatement bankStatement) {
		return bankStatementRepository.save(bankStatement);
	}
	
	

}

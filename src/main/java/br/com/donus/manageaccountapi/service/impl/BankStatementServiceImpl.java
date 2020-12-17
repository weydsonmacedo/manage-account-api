package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.response.BankStatementDTO;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankStatement;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.repository.BankStatementRepository;
import br.com.donus.manageaccountapi.service.BankStatementService;

@Service
public class BankStatementServiceImpl implements BankStatementService {

	@Autowired
	BankStatementRepository bankStatementRepository;
	
	@Autowired
	Utilities utilities;
	
	@Override
	public BankStatement generateBankStatement(BankTransaction bankMovement, BankAccount bankAccount,
			BigDecimal previousBalance, BigDecimal currentBalance) {
		BankStatement bs = new BankStatement();
		bs.setBankAccount(bankAccount);
		bs.setBankTransaction(bankMovement);
		bs.setPreviousBalance(previousBalance);
		bs.setCurrentBalance(currentBalance);
		bs.setCreationDate(LocalDateTime.now());
		return this.save(bs);
	}

	@Override
	public BankStatement save(BankStatement bankStatement) {
		return bankStatementRepository.save(bankStatement);
	}

	@Override
	public BankStatementDTO getStatement(String cpf) {
		BankAccount bacc = utilities.findByCpf(cpf);
		List<BankStatement> ListStatement = bankStatementRepository.findByBankAccount(bacc);
		return Utilities.parseListToBankStatementDTO(ListStatement);
	}
	
	

}

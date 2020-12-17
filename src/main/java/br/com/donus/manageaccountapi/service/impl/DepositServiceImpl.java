package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.request.DepositDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankMovement;
import br.com.donus.manageaccountapi.model.MovementType;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.BankMovementService;
import br.com.donus.manageaccountapi.service.BankStatementService;
import br.com.donus.manageaccountapi.service.DepositService;

@Service
public class DepositServiceImpl implements DepositService {
	Logger logger = LoggerFactory.getLogger(DepositServiceImpl.class);
	
	@Autowired
	BankAccountService baccService;
	
	@Autowired
	Utilities utilities;
	
	@Autowired
	BankMovementService bankMovementService;
	
	@Autowired
	BankStatementService  bankStatementService;
	
	@Override
	public ResponseTransactionInfoDTO deposit(DepositDTO dep) {
		BankAccount bacc = utilities.findByCpf(dep.getCpfToDeposit());
		BigDecimal previousBalance = bacc.getBalance();
		doDeposit(bacc, dep);
		BankMovement movement = bankMovementService.movement(bacc, bacc, MovementType.DEPOSIT, dep.getValue());
		 bacc = baccService.save(bacc);
		bankStatementService.generateBankStatement(movement, bacc, previousBalance, bacc.getBalance());
		ResponseTransactionInfoDTO response = Utilities.parseEntityToResponseTransactionInfoDTO(bacc);
		response.setTransactionId(movement.getTransactionId());
		return response;
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

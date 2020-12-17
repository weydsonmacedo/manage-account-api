package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.request.WithdrawDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.model.TransactionType;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.BankStatementService;
import br.com.donus.manageaccountapi.service.BankTransactionService;
import br.com.donus.manageaccountapi.service.WithdrawService;

@Service
public class WithdrawServiceImpl implements WithdrawService {
	Logger logger = LoggerFactory.getLogger(WithdrawServiceImpl.class);
	
	@Autowired
	BankAccountService baccService;
	
	@Autowired
	Utilities utilities;
	
	@Autowired
	BankTransactionService bankTransactionService;
	
	@Autowired
	BankStatementService  bankStatementService;
	
	@Override
	public ResponseTransactionInfoDTO draw(WithdrawDTO draw) {
		BankAccount bacc = utilities.findByCpf(draw.getCpfWithdraw());
		BigDecimal previousBalance = bacc.getBalance();
		withdrawMoney(draw,bacc);
		 bacc = baccService.save(bacc);
		 BankTransaction transaction = generateTransactionAndStatement(draw, bacc, previousBalance);
		return Utilities.parseEntityToResponseTransactionInfoDTO(bacc,transaction.getTransactionCode());
	}
	
	private void withdrawMoney(WithdrawDTO draw, BankAccount bacc) {
		String currentBalance = bacc.getBalance().toString();
		validateBalanceForWithdraw(draw,bacc,"SEU SALDO É INSUFICIENTE PARA REALIZAR ESTA OPERAÇÃO.",currentBalance);
		bacc.setBalance(bacc.getBalance().subtract(draw.getValue()));
		validateBalanceForWithdraw(draw,bacc,"SALDO INSUFICIENTE PARA REALIZAR ESTA OPERAÇÃO. ATENTE-SE PARA A TAXA DE 1%.",currentBalance);
		BigDecimal fee = bankFee(draw);
		bacc.setBalance(bacc.getBalance().subtract(fee));
		
	}
	

	private BigDecimal bankFee(WithdrawDTO draw) {
		return draw.getValue().multiply( new BigDecimal("0.01"));
		
	}

	private void validateBalanceForWithdraw(WithdrawDTO draw, BankAccount bacc, String message, String currentBalance) {
		if (bacc.getBalance().compareTo(draw.getValue()) == -1) {
			String msg = message.concat(" SALDO ATUAL: ").concat(currentBalance);
			logger.error("SALDO INSUFICIENTE: ",currentBalance);
			throw new BussinessException(HttpStatus.PRECONDITION_FAILED,msg);
		}
	}
	
	private BankTransaction generateTransactionAndStatement(WithdrawDTO draw, BankAccount bacc,
			BigDecimal previousBalance) {
		BigDecimal fee = bankFee(draw);
		BankTransaction transaction = bankTransactionService.transact(bacc, TransactionType.WITHDRAW, draw.getValue(), BigDecimal.ZERO,fee);
		bankStatementService.generateBankStatement(transaction, bacc, previousBalance, bacc.getBalance());
		return transaction;
	}
	

}

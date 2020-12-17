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
import br.com.donus.manageaccountapi.repository.BankAccountRepository;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.DrawService;

@Service
public class DrawServiceImpl implements DrawService {
	Logger logger = LoggerFactory.getLogger(DrawServiceImpl.class);
	
	@Autowired
	BankAccountService baccService;
	
	@Autowired
	Utilities utilities;
	
	@Override
	public ResponseTransactionInfoDTO draw(WithdrawDTO saq) {
		BankAccount cb = utilities.findByCpf(saq.getCpfWithdraw());
		withdrawMoney(saq,cb);
		return Utilities.parseEntityToResponseTransactionInfoDTO(baccService.save(cb));
	}
	
	private void withdrawMoney(WithdrawDTO saq, BankAccount cb) {
		String currentBalance = cb.getBalance().toString();
		validateBalanceForWithdraw(saq,cb,"SEU SALDO É INSUFICIENTE PARA REALIZAR ESTA OPERAÇÃO.",currentBalance);
		cb.setBalance(cb.getBalance().subtract(saq.getValue()));
		validateBalanceForWithdraw(saq,cb,"SALDO INSUFICIENTE PARA REALIZAR ESTA OPERAÇÃO. ATENTE-SE PARA A TAXA DE 1%.",currentBalance);
		bankFee(saq,cb);
		
	}
	

	private void bankFee(WithdrawDTO saq, BankAccount cb) {
		BigDecimal fee = saq.getValue().multiply( new BigDecimal("0.01"));
		cb.setBalance(cb.getBalance().subtract(fee));
		
	}

	private void validateBalanceForWithdraw(WithdrawDTO saq, BankAccount cb, String message, String currentBalance) {
		if (cb.getBalance().compareTo(saq.getValue()) == -1) {
			String msg = message.concat(" SALDO ATUAL: ").concat(currentBalance);
			logger.error("SALDO INSUFICIENTE: ",currentBalance);
			throw new BussinessException(HttpStatus.PRECONDITION_FAILED,msg);
		}
	}
	

}

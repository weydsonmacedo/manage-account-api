package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.ContaBancariaInfoDTO;
import br.com.donus.manageaccountapi.dto.SaqueDTO;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.repository.BankAccountRepository;
import br.com.donus.manageaccountapi.service.DrawService;

@Service
public class DrawServiceImpl implements DrawService {
	Logger logger = LoggerFactory.getLogger(DrawServiceImpl.class);
	
	@Autowired
	BankAccountRepository cbRepository;
	
	@Override
	public ContaBancariaInfoDTO draw(SaqueDTO saq) {
		BankAccount cb = findByCpf(saq.getCpfSaque());
		withdrawMoney(saq,cb);
		return Utilities.parseEntityToDTO(cbRepository.save(cb));
	}
	
	private void withdrawMoney(SaqueDTO saq, BankAccount cb) {
		validateBalanceForWithdraw(saq,cb);
		cb.setSaldo(cb.getSaldo().subtract(saq.getValor()));
		bankFee(saq,cb);
		
	}
	

	private void bankFee(SaqueDTO saq, BankAccount cb) {
		BigDecimal fee = saq.getValor().multiply( new BigDecimal("0.01"));
		cb.setSaldo(cb.getSaldo().subtract(fee));
		
	}

	private void validateBalanceForWithdraw(SaqueDTO saq, BankAccount cb) {
		if (cb.getSaldo().compareTo(saq.getValor()) == -1) {
			String msg = "SEU SALDO É INSUFICIENTE PARA REALIZAR ESTA OPERAÇÃO. SALDO ATUAL: ".concat(cb.getSaldo().toString());
			logger.error("SALDO INSUFICIENTE: ",cb.getSaldo());
			throw new BussinessException(HttpStatus.PRECONDITION_FAILED,msg);
		}
	}
	

	private BankAccount findByCpf(String cpf) {
		BankAccount cb = cbRepository.findByCpf(cpf);
		if (cb == null ) {
			String msg = "O CPF DE NÚMERO: ".concat(cpf).concat(" NÃO EXISTE NA BASE DE DADOS");
			logger.error("CPF INEXISTENTE: ",cpf);
			throw new ResourceNotFoundException(msg);
		}
		return cb;
	}

}

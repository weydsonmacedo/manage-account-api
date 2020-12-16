package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.ContaBancariaInfoDTO;
import br.com.donus.manageaccountapi.dto.DepositoDTO;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.repository.BankAccountRepository;
import br.com.donus.manageaccountapi.service.DepositService;

@Service
public class DepositServiceImpl implements DepositService {
	Logger logger = LoggerFactory.getLogger(DepositServiceImpl.class);
	@Autowired
	BankAccountRepository cbRepository;
	
	@Override
	public ContaBancariaInfoDTO deposit(DepositoDTO dep) {
		BankAccount cb = findByCpf(dep.getCpfDepositante());
		doDeposit(cb, dep);
		return Utilities.parseEntityToDTO(cbRepository.save(cb));
	}
	
	private void doDeposit(BankAccount cb, DepositoDTO dep) {

		cb.setSaldo(cb.getSaldo().add(dep.getValor()));

		BigDecimal bonus = getBonus(dep);
		
		cb.setSaldo(cb.getSaldo().add(bonus));

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
	
	private BigDecimal getBonus(DepositoDTO dep) {
		MathContext mc = new MathContext(4);
		BigDecimal bonus = dep.getValor().multiply(new BigDecimal("0.005"), mc);
		return bonus;
	}
	
}

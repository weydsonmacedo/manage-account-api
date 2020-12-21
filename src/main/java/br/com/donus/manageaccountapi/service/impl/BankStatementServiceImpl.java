package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.response.BankStatementDTO;
import br.com.donus.manageaccountapi.dto.response.StatementDTO;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankStatement;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.repository.BankStatementRepository;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.BankStatementService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BankStatementServiceImpl implements BankStatementService {

	private BankStatementRepository bankStatementRepository;
	
	private BankAccountService baccService;
	


	public BankStatementServiceImpl(BankStatementRepository bankStatementRepository, BankAccountService baccService) {
		this.bankStatementRepository = bankStatementRepository;
		this.baccService = baccService;
	}

	@Override
	public BankStatement generateBankStatement(BankTransaction bankTransaction, BankAccount bankAccount,
			BigDecimal previousBalance, BigDecimal currentBalance) {
		BankStatement bs = new BankStatement();
		bs.setBankAccount(bankAccount);
		bs.setBankTransaction(bankTransaction);
		bs.setPreviousBalance(previousBalance);
		bs.setCurrentBalance(currentBalance);
		bs.setCreationDate(LocalDateTime.now());
		return this.save(bs);
	}

	private BankStatement save(BankStatement bankStatement) {
		return bankStatementRepository.save(bankStatement);
	}

	@Override
	public BankStatementDTO getStatement(String cpf) {
		BankAccount bacc = baccService.findByCpf(cpf);
		//List<BankStatement> ListStatement = bankStatementRepository.findByBankAccount(bacc);
		List<StatementDTO> list = bankStatementRepository.findByIdBankAccount(bacc.getId());
		validateEmptyList(list);
		return Utilities.parseListToBankStatementDTO(list, bacc);
	}

	private void validateEmptyList(List<StatementDTO> list) {
		if (list.isEmpty()) {
			log.error("NÃO FORAM ENCONTRADOS EXTRATOS PARA ESSA CONTA: ");
			throw new  BussinessException(HttpStatus.NO_CONTENT,"NÃO FORAM ENCONTRADOS EXTRATOS PARA ESSA CONTA");
		}
	}
	
	

}

package br.com.donus.manageaccountapi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.ContaBancariaDTO;
import br.com.donus.manageaccountapi.dto.ContaBancariaInfoDTO;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.repository.BankAccountRepository;
import br.com.donus.manageaccountapi.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService{

	Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);
	
	@Autowired
	BankAccountRepository cbRepository;
	
	@Override
	public ContaBancariaInfoDTO create(ContaBancariaDTO cbDTO) {
		BankAccount cb = Utilities.parseDtoToEntity(cbDTO);
		return Utilities.parseEntityToDTO(cbRepository.save(cb));
	}

}

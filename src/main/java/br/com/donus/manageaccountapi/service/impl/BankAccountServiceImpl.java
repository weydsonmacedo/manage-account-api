package br.com.donus.manageaccountapi.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.repository.BankAccountRepository;
import br.com.donus.manageaccountapi.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService{

	Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);
	
	@Autowired
	BankAccountRepository cbRepository;
	
	@Override
	public BankAccountInfoDTO create(BankAccountDTO cbDTO) {
		BankAccount cb = Utilities.parseDtoToEntity(cbDTO);
		cb.setCreationDate(LocalDateTime.now());
		return Utilities.parseEntityToDTO(this.save(cb));
	}

	@Override
	public BankAccount save(BankAccount entity) {
		entity.setModifiedDate(LocalDateTime.now());
		return cbRepository.save(entity);
	}
	
	

}

package br.com.donus.manageaccountapi.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.repository.BankAccountRepository;
import br.com.donus.manageaccountapi.service.BankAccountService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BankAccountServiceImpl implements BankAccountService{

	private BankAccountRepository baccRepository;
	
	
	public BankAccountServiceImpl(BankAccountRepository baccRepository) {
		this.baccRepository = baccRepository;
	}

	@Override
	public BankAccountInfoDTO create(BankAccountDTO cbDTO) {
		BankAccount cb = Utilities.parseDtoToEntity(cbDTO);
		cb.setCreationDate(LocalDateTime.now());
		return Utilities.parseEntityToDTO(this.save(cb));			
	}

	@Override
	public BankAccount update(BankAccount entity) {
		return this.save(entity);
	}
	
	private BankAccount save(BankAccount entity) {
		entity.setModifiedDate(LocalDateTime.now());
		return baccRepository.save(entity);
	}
	
	@Override
	public BankAccount findByCpf(String cpf) {
		BankAccount cb = baccRepository.findByCpf(cpf);
		if (cb == null ) {
			String msg = "O CPF DE NÚMERO: ".concat(cpf).concat(" NÃO EXISTE NA BASE DE DADOS");
			log.error("CPF INEXISTENTE: ",cpf);
			throw new ResourceNotFoundException(msg);
		}
		return cb;
	}
	

}

package br.com.donus.manageaccountapi.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.Status;
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
	public BankAccountInfoDTO create(BankAccountDTO baccDTO) {
		BankAccount found = baccRepository.findByCpf(baccDTO.getCpf());
		if(found == null) {		
			return createNew(baccDTO);			
		}
		accountAlreadyExists(found);
		return reactivateAccount(found);
	}

	private BankAccountInfoDTO reactivateAccount(BankAccount found) {
		found.setStatus(Status.ACTIVE);
		return Utilities.parseEntityToDTO(this.update(found));
	}

	private void accountAlreadyExists(BankAccount found) {
		if (found.getStatus().equals(Status.ACTIVE)) {
			log.error("ESTA CONTA JÁ SE ENCONTRA NA BASE DE DADOS!: ",found.getCpf());
			throw new BussinessException(HttpStatus.CONFLICT, "ESTA CONTA JÁ SE ENCONTRA NA BASE DE DADOS!");
		}
	}

	private BankAccountInfoDTO createNew(BankAccountDTO baccDTO) {
		BankAccount bacc = Utilities.parseDtoToEntity(baccDTO);
		bacc.setCreationDate(LocalDateTime.now());
		bacc.setStatus(Status.ACTIVE);
		return Utilities.parseEntityToDTO(this.save(bacc));
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
		BankAccount cb = baccRepository.findByCpfAndStatus(cpf,Status.ACTIVE);
		if (cb == null ) {
			String msg = "O CPF DE NÚMERO: ".concat(cpf).concat(" NÃO EXISTE NA BASE DE DADOS");
			log.error("CPF INEXISTENTE: ",cpf);
			throw new ResourceNotFoundException(msg);
		}
		return cb;
	}

	@Override
	public String delete(String cpf) {
		 BankAccount entityBacc = this.findByCpf(cpf);
		 entityBacc.setStatus(Status.INACTIVE);
		this.update(entityBacc);
		 return "CONTA BANCÁRIA DELETADA COM SUCESSO";
	}
	

}

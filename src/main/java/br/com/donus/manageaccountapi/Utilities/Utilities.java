package br.com.donus.manageaccountapi.Utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.repository.BankAccountRepository;

@Service
public class Utilities {
	Logger logger = LoggerFactory.getLogger(Utilities.class);
	
	@Autowired
	BankAccountRepository cbRepository;
	
	public static BankAccountInfoDTO parseEntityToDTO(BankAccount entity) {
		BankAccountInfoDTO cbDTO = new BankAccountInfoDTO();
		cbDTO.setCpf(entity.getCpf());
		cbDTO.setName(entity.getName());
		cbDTO.setBalance(validateBalance(entity));
		return cbDTO;
	}
	
	public static ResponseTransactionInfoDTO parseEntityToResponseTransactionInfoDTO(BankAccount entity) {
		ResponseTransactionInfoDTO cbDTO = new ResponseTransactionInfoDTO();
		cbDTO.setCpf(entity.getCpf());
		cbDTO.setName(entity.getName());
		cbDTO.setBalance(validateBalance(entity));
		return cbDTO;
	}
	
	public static BigDecimal validateBalance(BankAccount entity) {
		return entity.getBalance() != null? entity.getBalance().setScale(2, RoundingMode.UP): BigDecimal.ZERO;
	}
	
	public static BankAccount parseDtoToEntity(BankAccountDTO cbDTO) {
		BankAccount cb = new BankAccount();
		cb.setName(cbDTO.getName());
		cb.setCpf(cbDTO.getCpf());
		return cb;
	}
	
	public BankAccount findByCpf(String cpf) {
		BankAccount cb = cbRepository.findByCpf(cpf);
		if (cb == null ) {
			String msg = "O CPF DE NÚMERO: ".concat(cpf).concat(" NÃO EXISTE NA BASE DE DADOS");
			logger.error("CPF INEXISTENTE: ",cpf);
			throw new ResourceNotFoundException(msg);
		}
		return cb;
	}
	
	public static BankAccountDTO parseEntityToBankAccountDTO(BankAccount entity) {
		BankAccountDTO cbDTO = new BankAccountDTO();
		cbDTO.setCpf(entity.getCpf());
		cbDTO.setName(entity.getName());
		return cbDTO;
	}
}

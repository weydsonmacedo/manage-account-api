package br.com.donus.manageaccountapi.service;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.model.BankAccount;

public interface BankAccountService {

	public BankAccountInfoDTO create(BankAccountDTO cbDTO);
	
	public BankAccount update(BankAccount entity);
	
	public BankAccount findByCpf(String cpf);
	
	public String delete(String cpf);
}

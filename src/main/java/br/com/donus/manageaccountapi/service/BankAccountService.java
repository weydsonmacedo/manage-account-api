package br.com.donus.manageaccountapi.service;

import br.com.donus.manageaccountapi.dto.ContaBancariaDTO;
import br.com.donus.manageaccountapi.dto.ContaBancariaInfoDTO;

public interface BankAccountService {

	public ContaBancariaInfoDTO create(ContaBancariaDTO cbDTO);
}

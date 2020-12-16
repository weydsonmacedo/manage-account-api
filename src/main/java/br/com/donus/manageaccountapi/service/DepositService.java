package br.com.donus.manageaccountapi.service;

import br.com.donus.manageaccountapi.dto.ContaBancariaInfoDTO;
import br.com.donus.manageaccountapi.dto.DepositoDTO;

public interface DepositService {
	public ContaBancariaInfoDTO deposit(DepositoDTO dep);
}

package br.com.donus.manageaccountapi.service;

import br.com.donus.manageaccountapi.dto.request.DepositDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;

public interface DepositService {
	public ResponseTransactionInfoDTO deposit(DepositDTO dep);
}

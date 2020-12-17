package br.com.donus.manageaccountapi.service;

import br.com.donus.manageaccountapi.dto.request.WithdrawDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;

public interface WithdrawService {
	public ResponseTransactionInfoDTO draw(WithdrawDTO saq);
}

package br.com.donus.manageaccountapi.service;

import br.com.donus.manageaccountapi.dto.request.BankTransferDTO;
import br.com.donus.manageaccountapi.dto.response.TransferResponseDTO;

public interface BankTransferService {
	
	public TransferResponseDTO transfer(BankTransferDTO bt);	
}

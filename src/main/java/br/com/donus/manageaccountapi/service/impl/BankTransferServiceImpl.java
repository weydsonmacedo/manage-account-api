package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.request.BankTransferDTO;
import br.com.donus.manageaccountapi.dto.response.TransferResponseDTO;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankMovement;
import br.com.donus.manageaccountapi.model.MovementType;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.BankMovementService;
import br.com.donus.manageaccountapi.service.BankStatementService;
import br.com.donus.manageaccountapi.service.BankTransferService;

@Service
public class BankTransferServiceImpl implements BankTransferService {
	Logger logger = LoggerFactory.getLogger(BankTransferServiceImpl.class);
	
	@Autowired
	Utilities utilities;
	
	@Autowired
	BankAccountService bankAccountService;
	
	@Autowired
	BankMovementService bankMovementService;
	
	@Autowired
	BankStatementService  bankStatementService;

	@Override
	public TransferResponseDTO transfer(BankTransferDTO bt) {
		BankAccount baDonor = utilities.findByCpf(bt.getCpfDonor());
		BankAccount baReceiver = utilities.findByCpf(bt.getCpfReceiver());
		BigDecimal previousBalanceDonor = baDonor.getBalance();
		BigDecimal previousBalanceReceiver = baReceiver.getBalance();
		
		baDonor = payTransfer(baDonor,bt);
		baReceiver = receiverTransfer(baReceiver,bt);
		
		BankMovement movement = bankMovementService.movement(baDonor, baReceiver, MovementType.TRANSFER, bt.getValue());
		
		bankStatementService.generateBankStatement(movement, baDonor, previousBalanceDonor, baDonor.getBalance());
		bankStatementService.generateBankStatement(movement, baReceiver, previousBalanceReceiver, baReceiver.getBalance());
		
		TransferResponseDTO response = generateTransferResponseDTO(bt, baDonor, baReceiver, movement);
		return response;
	}

	private TransferResponseDTO generateTransferResponseDTO(BankTransferDTO bt, BankAccount baDonor,
			BankAccount baReceiver, BankMovement movement) {
		BankAccountDTO donorDto = Utilities.parseEntityToBankAccountDTO(baDonor);
		BankAccountDTO receiverDto = Utilities.parseEntityToBankAccountDTO(baReceiver);
		TransferResponseDTO response = new TransferResponseDTO(movement.getTransactionId(), donorDto, receiverDto, bt.getValue());
		return response;
	}
	
	private BankAccount payTransfer(BankAccount baDonor, BankTransferDTO bt) {
		validateBalance(baDonor.getBalance(),bt.getValue());
		baDonor.setBalance(baDonor.getBalance().subtract(bt.getValue()));
		return bankAccountService.save(baDonor);
	}
	
	private void validateBalance(BigDecimal balance, BigDecimal value) {
		if (balance.compareTo(value) == -1) {
			String msg = "SEU SALDO É INSUFICIENTE PARA REALIZAR ESTA OPERAÇÃO. SALDO ATUAL: ".concat(balance.toString());
			logger.error("SALDO INSUFICIENTE: ",balance);
			throw new BussinessException(HttpStatus.PRECONDITION_FAILED,msg);
		}
	}

	private BankAccount receiverTransfer(BankAccount baReceiver, BankTransferDTO bt) {
		baReceiver.setBalance(baReceiver.getBalance().add(bt.getValue()));
		return bankAccountService.save(baReceiver);
	}
}

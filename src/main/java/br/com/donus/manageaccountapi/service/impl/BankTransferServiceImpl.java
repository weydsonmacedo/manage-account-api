package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.Utilities.Utilities;
import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.request.BankTransferDTO;
import br.com.donus.manageaccountapi.dto.response.TransferResponseDTO;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.model.TransactionType;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.BankStatementService;
import br.com.donus.manageaccountapi.service.BankTransactionService;
import br.com.donus.manageaccountapi.service.BankTransferService;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class BankTransferServiceImpl implements BankTransferService {
	
	
	private BankAccountService bankAccountService;
	
	private BankTransactionService bankTransactionService;
	
	private BankStatementService  bankStatementService;

	public BankTransferServiceImpl(BankAccountService bankAccountService, BankTransactionService bankTransactionService,
			BankStatementService bankStatementService) {
		this.bankAccountService = bankAccountService;
		this.bankTransactionService = bankTransactionService;
		this.bankStatementService = bankStatementService;
	}

	@Override
	public TransferResponseDTO transfer(BankTransferDTO bktransDTO) {
		validateSameAccount(bktransDTO);
		BankAccount baDonor = bankAccountService.findByCpf(bktransDTO.getCpfDonor());
		BankAccount baReceiver = bankAccountService.findByCpf(bktransDTO.getCpfReceiver());
		BigDecimal previousBalanceDonor = baDonor.getBalance();
		BigDecimal previousBalanceReceiver = baReceiver.getBalance();
		
		baDonor = payTransfer(baDonor,bktransDTO);
		baReceiver = receiverTransfer(baReceiver,bktransDTO);
		
		BankTransaction transaction = generateTransactionAndStatements(bktransDTO, baDonor, baReceiver, previousBalanceDonor,previousBalanceReceiver);
		
		TransferResponseDTO response = generateTransferResponseDTO(bktransDTO, baDonor, baReceiver, transaction);
		return response;
	}

	private void validateSameAccount(BankTransferDTO bktransDTO) {
		if (bktransDTO.getCpfDonor().equals(bktransDTO.getCpfReceiver())) {
			String msg = "CONTAS IGUAIS! NÃO É PERMITIDO A TRANSFERÊNCIA PARA A MESMA CONTA. CPF: ".concat(bktransDTO.getCpfReceiver());
			log.error("CONTAS IGUAIS! NÃO É PERMITIDO A TRANSFERÊNCIA PARA A MESMA CONTA. CPF: ",bktransDTO.getCpfReceiver());
			throw new BussinessException(HttpStatus.PRECONDITION_FAILED,msg);
		}
		
	}

	private BankTransaction generateTransactionAndStatements(BankTransferDTO bt, BankAccount baDonor,
			BankAccount baReceiver, BigDecimal previousBalanceDonor, BigDecimal previousBalanceReceiver) {
		BankTransaction transaction = bankTransactionService.transact(baDonor, baReceiver, TransactionType.TRANSFER, bt.getValue(),BigDecimal.ZERO,BigDecimal.ZERO);
		
		bankStatementService.generateBankStatement(transaction, baDonor, previousBalanceDonor, baDonor.getBalance());
		bankStatementService.generateBankStatement(transaction, baReceiver, previousBalanceReceiver, baReceiver.getBalance());
		return transaction;
	}

	private TransferResponseDTO generateTransferResponseDTO(BankTransferDTO bt, BankAccount baDonor,
			BankAccount baReceiver, BankTransaction transaction) {
		BankAccountDTO donorDto = Utilities.parseEntityToBankAccountDTO(baDonor);
		BankAccountDTO receiverDto = Utilities.parseEntityToBankAccountDTO(baReceiver);
		TransferResponseDTO response = new TransferResponseDTO(transaction.getTransactionCode(), donorDto, receiverDto, bt.getValue());
		return response;
	}
	
	private BankAccount payTransfer(BankAccount baDonor, BankTransferDTO bt) {
		validateBalance(baDonor.getBalance(),bt.getValue());
		baDonor.setBalance(baDonor.getBalance().subtract(bt.getValue()));
		return bankAccountService.update(baDonor);
	}

	private BankAccount receiverTransfer(BankAccount baReceiver, BankTransferDTO bt) {
		baReceiver.setBalance(baReceiver.getBalance().add(bt.getValue()));
		return bankAccountService.update(baReceiver);
	}
	
	private void validateBalance(BigDecimal balance, BigDecimal value) {
		if (balance.compareTo(value) == -1) {
			String msg = "SEU SALDO É INSUFICIENTE PARA REALIZAR ESTA OPERAÇÃO. SALDO ATUAL: ".concat(balance.toString());
			log.error("SALDO INSUFICIENTE: ", balance);
			throw new BussinessException(HttpStatus.PRECONDITION_FAILED, msg);
		}
	}
	
}

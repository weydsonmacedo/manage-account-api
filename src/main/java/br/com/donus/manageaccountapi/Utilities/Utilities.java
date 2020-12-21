package br.com.donus.manageaccountapi.Utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.dto.response.BankStatementDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;
import br.com.donus.manageaccountapi.dto.response.StatementDTO;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankStatement;

public class Utilities {
	
	
	public static BankAccountInfoDTO parseEntityToDTO(BankAccount entity) {
		BankAccountInfoDTO cbDTO = new BankAccountInfoDTO();
			cbDTO.setCpf(entity.getCpf());
			cbDTO.setName(entity.getName());
			cbDTO.setBalance(validateBalance(entity));
			cbDTO.setBankAccCreationDate(entity.getCreationDate());
			cbDTO.setAccountStatus(entity.getStatus());
		return cbDTO;
	}
	
	public static ResponseTransactionInfoDTO parseEntityToResponseTransactionInfoDTO(BankAccount entity, String transactionCode) {
		ResponseTransactionInfoDTO responseTransactDTO = new ResponseTransactionInfoDTO();
			responseTransactDTO.setCpf(entity.getCpf());
			responseTransactDTO.setName(entity.getName());
			responseTransactDTO.setBalance(validateBalance(entity));
			responseTransactDTO.setBankAccCreationDate(entity.getCreationDate());
			responseTransactDTO.setTransactionCode(transactionCode);
			responseTransactDTO.setAccountStatus(entity.getStatus());
		return responseTransactDTO;
	}
	
	public static BigDecimal validateBalance(BankAccount entity) {
		return entity.getBalance() != null? entity.getBalance().setScale(2, RoundingMode.UP): BigDecimal.ZERO;
	}
	
	public static BankAccount parseDtoToEntity(BankAccountDTO baccDTO) {
		BankAccount baccEntity = new BankAccount();
		baccEntity.setName(baccDTO.getName());
		baccEntity.setCpf(baccDTO.getCpf());
		return baccEntity;
	}	
	
	public static BankAccountDTO parseEntityToBankAccountDTO(BankAccount entity) {
		BankAccountDTO cbDTO = new BankAccountDTO();
		cbDTO.setCpf(entity.getCpf());
		cbDTO.setName(entity.getName());
		return cbDTO;
	}
	
	public static BankStatementDTO parseListToBankStatementDTO(List<BankStatement> listBankStatement) {
		BankAccountInfoDTO accInfo = Utilities.parseEntityToDTO(listBankStatement.stream().findFirst()
				.orElseThrow(
						() -> new BussinessException(HttpStatus.BAD_REQUEST, "OCORREU UM ERRO! LISTA DE EXTRATO VAZIA"))
				.getBankAccount());
		
		List<StatementDTO> statementDTOList = listBankStatement.stream().map(Utilities::parseToStatementDTO)
				.collect(Collectors.toList());
		BankStatementDTO statement = new BankStatementDTO(accInfo, statementDTOList);
		return statement;
	}

	public static StatementDTO parseToStatementDTO(BankStatement statement) {
		StatementDTO dto = new StatementDTO();
		dto.setTransactionCode(statement.getBankTransaction().getTransactionCode());
		dto.setPreviousBalance(statement.getPreviousBalance());
		dto.setValueTransaction(statement.getBankTransaction().getValue());
		dto.setBonification(statement.getBankTransaction().getBonification());
		dto.setFee(statement.getBankTransaction().getFee());
		dto.setCurrentBalance(statement.getCurrentBalance());
		dto.setTransactionType(statement.getBankTransaction().getTransactionType());
		dto.setOperationDate(statement.getBankTransaction().getCreationDate());
		dto.setCpfDonor(statement.getBankTransaction().getDonor().getCpf());
		dto.setNameDonor(statement.getBankTransaction().getDonor().getName());
		dto.setCpfReceiver(statement.getBankTransaction().getReceiver().getCpf());
		dto.setNameReceiver(statement.getBankTransaction().getReceiver().getName());
		return dto;
	}
}

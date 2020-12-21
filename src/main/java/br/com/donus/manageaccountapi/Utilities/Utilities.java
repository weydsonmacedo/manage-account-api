package br.com.donus.manageaccountapi.Utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.dto.response.BankStatementDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;
import br.com.donus.manageaccountapi.dto.response.StatementDTO;
import br.com.donus.manageaccountapi.model.BankAccount;

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
	
	public static BankStatementDTO parseListToBankStatementDTO(List<StatementDTO> list,BankAccount bacc) {
		BankAccountInfoDTO accInfo = Utilities.parseEntityToDTO(bacc);
		BankStatementDTO statement = new BankStatementDTO(accInfo, list);
		return statement;
	}

}

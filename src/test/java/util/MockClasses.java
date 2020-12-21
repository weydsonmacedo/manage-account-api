package util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.request.BankTransferDTO;
import br.com.donus.manageaccountapi.dto.request.DepositDTO;
import br.com.donus.manageaccountapi.dto.request.WithdrawDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.dto.response.BankStatementDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;
import br.com.donus.manageaccountapi.dto.response.StatementDTO;
import br.com.donus.manageaccountapi.dto.response.TransferResponseDTO;
import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankStatement;
import br.com.donus.manageaccountapi.model.BankTransaction;
import br.com.donus.manageaccountapi.model.Status;
import br.com.donus.manageaccountapi.model.TransactionType;

public class MockClasses {

	public static BankAccount getBankAccTestWithOutId() {
		return new BankAccount(null, "nome de teste", "75531547099", BigDecimal.ZERO,Status.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
	}
	
	public static BankAccount getBankAccTestWithId() {
		return new BankAccount(1L, "nome de teste 1", "75531547099", BigDecimal.ZERO,Status.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
	}
	
	public static BankAccount getBankAccTestWithId2() {
		return new BankAccount(2L, "nome de teste 2", "86267885097", BigDecimal.ONE,Status.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
	}
	
	public static BankAccount getBankAccount() {
		return new BankAccount(2L, "teste 2", "86267885097", BigDecimal.ONE,Status.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
	}
	
	public static BankAccount getBankAccountINACTIVE() {
		return new BankAccount(2L, "teste 3", "75531547099", BigDecimal.ONE,Status.INACTIVE, LocalDateTime.now(), LocalDateTime.now());
	}
	
	public static BankAccount getBankAccountTENOfBalance() {
		return new BankAccount(2L, "teste 2", "86267885097", BigDecimal.TEN,Status.ACTIVE, LocalDateTime.now(), LocalDateTime.now());
	}
	
	public static BankAccountDTO getBankAccountDTOTest() {
		return new BankAccountDTO("nome de teste 1","75531547099");
	}
	
	public static BankAccountDTO getBankAccountDTOTestWrongCPF() {
		return new BankAccountDTO("nome de teste 1","01234568901");
	}
	
	
	public static BankAccountDTO getBankAccountDTOTest2() {
		return new BankAccountDTO("nome de teste 2","86267885097");
	}
	
	public static BankAccountInfoDTO getBankAccountInfoDTO() {
		return new BankAccountInfoDTO("75531547099","nome de teste",BigDecimal.ZERO,LocalDateTime.now(),Status.ACTIVE);
	}
	
	public static ResponseTransactionInfoDTO getResponseTransactionInfoDTO() {
		ResponseTransactionInfoDTO	response = new ResponseTransactionInfoDTO("7cd7acc8-a777-493e-aa82-bb65cf21336a");
		response.setBalance(BigDecimal.TEN);
		response.setBankAccCreationDate(LocalDateTime.now());
		response.setCpf("75531547099");
		response.setName("test object");
		return response;
	}
	
	public static DepositDTO getDepositDTO() {
		return new DepositDTO("75531547099", BigDecimal.TEN);
	}
	
	public static WithdrawDTO getWithdrawDTO() {
		return new WithdrawDTO("75531547099", BigDecimal.TEN);
	}
	
	public static WithdrawDTO getWithdrawDTOOne() {
		return new WithdrawDTO("75531547099", BigDecimal.ONE);
	}
	
	public static WithdrawDTO getWithdrawDTOHUNDRED() {
		return new WithdrawDTO("75531547099", BigDecimal.valueOf(100));
	}
	
	
	public static BankStatementDTO getBankStatementDTO() {
		return new BankStatementDTO(getBankAccountInfoDTO(), List.of(getStatementDTO()));
	}
	
	public static StatementDTO getStatementDTO() {
		return new StatementDTO("7cd7acc8-a777-493e-aa82-bb65cf21336d",
				BigDecimal.ZERO, 
				BigDecimal.TEN, 
				BigDecimal.ZERO,
				BigDecimal.ONE, 
				BigDecimal.valueOf(11),
				TransactionType.DEPOSIT,
				LocalDateTime.now(),
				"75531547099",
				"teste", 
				"75531547099", 
				"teste");
	}
	
	public static BankTransferDTO getBankTransferDTO() {
		return new BankTransferDTO(getBankAccTestWithId().getCpf(), BigDecimal.ONE, getBankAccTestWithId2().getCpf());
	}
	public static BankTransferDTO getBankTransferDTOValueTen() {
		return new BankTransferDTO(getBankAccTestWithId().getCpf(), BigDecimal.TEN, getBankAccTestWithId2().getCpf());
	}
	
	public static BankTransferDTO getBankTransferDTOSameCPF() {
		return new BankTransferDTO(getBankAccTestWithId().getCpf(), BigDecimal.TEN, getBankAccTestWithId().getCpf());
	}
	
	public static TransferResponseDTO getTransferResponseDTO() {
		return new TransferResponseDTO("7cd7acc8-a777-493e-aa82-bb65cf21336b", getBankAccountDTOTest(), getBankAccountDTOTest2(), BigDecimal.ONE);
	}
	
	
	public static BankStatement getBankStatement() { 
		return new BankStatement(1L, getBankTransaction(), getBankAccTestWithId2(), BigDecimal.TEN, BigDecimal.ONE, LocalDateTime.now());	
	}
	
	
	public static List<StatementDTO> getListStatementDTO() { 
		return List.of(getStatementDTO(), getStatementDTO());	
	}
	
	public static BankTransaction getBankTransaction() {
		return new BankTransaction(1L, java.util.UUID.randomUUID(), getBankAccTestWithId(),
				TransactionType.TRANSFER, getBankAccTestWithId2(), BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO, LocalDateTime.now());	
				
	}
	
	
	
}

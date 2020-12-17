package br.com.donus.manageaccountapi.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.donus.manageaccountapi.model.TransactionType;
import lombok.Data;

@Data
public class StatementDTO {

	private String transactionCode;
	private BigDecimal previousBalance;
	private BigDecimal valueTransaction;
	private BigDecimal fee;
	private BigDecimal bonification;
	private BigDecimal currentBalance; 
	private TransactionType transactionType;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime operationDate;
	private String cpfDonor;
	private String nameDonor;	
	private String cpfReceiver;
	private String nameReceiver;
}

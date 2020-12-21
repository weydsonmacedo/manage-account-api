package br.com.donus.manageaccountapi.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.donus.manageaccountapi.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
	public StatementDTO(UUID transactionCode, BigDecimal previousBalance, BigDecimal valueTransaction, BigDecimal fee,
			BigDecimal bonification, BigDecimal currentBalance, TransactionType transactionType,
			LocalDateTime operationDate, String cpfDonor, String nameDonor, String cpfReceiver, String nameReceiver) {
		super();
		this.transactionCode = transactionCode.toString();
		this.previousBalance = previousBalance;
		this.valueTransaction = valueTransaction;
		this.fee = fee;
		this.bonification = bonification;
		this.currentBalance = currentBalance;
		this.transactionType = transactionType;
		this.operationDate = operationDate;
		this.cpfDonor = cpfDonor;
		this.nameDonor = nameDonor;
		this.cpfReceiver = cpfReceiver;
		this.nameReceiver = nameReceiver;
	}
	
	
}

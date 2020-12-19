package br.com.donus.manageaccountapi.dto.response;

import java.math.BigDecimal;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponseDTO {

	private String transactionCode;
	
	private BankAccountDTO accountBankDonor;
	
	private BankAccountDTO accountBankReceiver;
	
	private BigDecimal transferedValue;
}

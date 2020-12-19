package br.com.donus.manageaccountapi.dto.response;

import java.math.BigDecimal;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponseDTO {

	private String transactionCode;
	
	private BankAccountDTO bankAccountDonor;
	
	private BankAccountDTO bankAccountReceiver;
	
	private BigDecimal transferedValue;

	
}

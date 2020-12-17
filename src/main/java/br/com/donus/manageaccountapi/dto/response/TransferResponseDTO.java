package br.com.donus.manageaccountapi.dto.response;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponseDTO {

	private String transactionCode;
	
	private BankAccountDTO accountBankDonor;
	
	private BankAccountDTO accountBankReceiver;
	
	private BigDecimal transferedValue;
}

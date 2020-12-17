package br.com.donus.manageaccountapi.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTransactionInfoDTO extends BankAccountInfoDTO {

	private Long transactionId;
}

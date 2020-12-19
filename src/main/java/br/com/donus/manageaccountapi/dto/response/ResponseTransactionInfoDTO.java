package br.com.donus.manageaccountapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTransactionInfoDTO extends BankAccountInfoDTO {

	private String transactionCode;
}

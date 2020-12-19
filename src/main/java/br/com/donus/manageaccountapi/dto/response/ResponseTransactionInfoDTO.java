package br.com.donus.manageaccountapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ResponseTransactionInfoDTO extends BankAccountInfoDTO {

	private String transactionCode;
}

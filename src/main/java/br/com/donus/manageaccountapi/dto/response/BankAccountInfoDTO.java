package br.com.donus.manageaccountapi.dto.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankAccountInfoDTO {

	private String cpf;
	private String name;
	private BigDecimal balance;

}

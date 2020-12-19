package br.com.donus.manageaccountapi.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankStatementDTO {

	private BankAccountInfoDTO bankAccount;
	private List<StatementDTO> bankStatement;
}

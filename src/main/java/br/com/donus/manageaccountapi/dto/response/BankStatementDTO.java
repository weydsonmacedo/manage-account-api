package br.com.donus.manageaccountapi.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankStatementDTO {

	private BankAccountInfoDTO bankAccount;
	private List<StatementDTO> bankStatement;
}

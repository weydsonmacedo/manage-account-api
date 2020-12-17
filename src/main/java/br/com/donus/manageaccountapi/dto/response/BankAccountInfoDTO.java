package br.com.donus.manageaccountapi.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
	private LocalDateTime bankAccCreationDate;

}

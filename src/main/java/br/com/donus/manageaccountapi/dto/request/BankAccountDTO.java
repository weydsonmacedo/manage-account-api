package br.com.donus.manageaccountapi.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public class BankAccountDTO {

	@NotBlank
	@Size(min=2, max=150)
	private String name;
	
	@NotBlank
	@CPF
	private String cpf;
	
}

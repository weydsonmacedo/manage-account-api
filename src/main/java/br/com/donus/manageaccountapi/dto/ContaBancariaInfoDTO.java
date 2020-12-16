package br.com.donus.manageaccountapi.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ContaBancariaInfoDTO {

	private long id;
	private String cpf;
	private String nome;
	private BigDecimal saldo;
	
}

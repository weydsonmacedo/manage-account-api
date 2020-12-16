package br.com.donus.manageaccountapi.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepositoDTO {
	
	@NotBlank(message = "cpf do depositante é obrigatório")
	private String cpfDepositante;

	@Min(1)
	@NotNull(message = "valor para depósito é obrigatório")
	private BigDecimal valor;
}

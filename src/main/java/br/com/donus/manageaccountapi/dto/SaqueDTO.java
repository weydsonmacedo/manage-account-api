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
public class SaqueDTO {
	
	@NotBlank(message = "cpf da conta para saque é obrigatório")
	private String cpfSaque;

	@Min(1)
	@NotNull(message = "valor para saque é obrigatório")
	private BigDecimal valor;
}

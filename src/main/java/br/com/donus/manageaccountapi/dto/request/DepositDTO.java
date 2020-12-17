package br.com.donus.manageaccountapi.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepositDTO {
	
	@NotBlank(message = "cpf do depositante é obrigatório")
	@Size(min = 11, max = 11, message = "cpf deve ser válido e conter somente números")
	private String cpfToDeposit;

	@Min(1)
	@NotNull(message = "valor para depósito é obrigatório")
	private BigDecimal value;

}

package br.com.donus.manageaccountapi.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawDTO {
	
	@NotBlank(message = "cpf da conta para saque é obrigatório")
	@Size(min = 11, max = 11, message = "cpf deve ser válido e conter somente números")
	private String cpfWithdraw;

	@Min(1)
	@NotNull(message = "valor para saque é obrigatório")
	private BigDecimal value;

}

package br.com.donus.manageaccountapi.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankTransferDTO {

	@NotBlank(message = "cpf do doador é obrigatório")
	@Size(min = 11, max = 11, message = "cpf deve ser válido e conter somente números")
	private String cpfDonor;
	
	@Min(1)
	private BigDecimal value;
	
	@NotBlank(message = "cpf do recebedor é obrigatório")
	private String cpfReceiver;
	
}

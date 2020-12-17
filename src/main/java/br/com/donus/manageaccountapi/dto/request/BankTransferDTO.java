package br.com.donus.manageaccountapi.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankTransferDTO {

	@NotBlank(message = "cpf do doador é obrigatório")
	private String cpfDonor;
	
	@Min(1)
	private BigDecimal value;
	
	@NotBlank(message = "cpf do recebedor é obrigatório")
	private String cpfReceiver;
	
}

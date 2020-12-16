package br.com.donus.manageaccountapi.Utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.dto.ContaBancariaDTO;
import br.com.donus.manageaccountapi.dto.ContaBancariaInfoDTO;
import br.com.donus.manageaccountapi.model.BankAccount;

@Service
public class Utilities {

	public static ContaBancariaInfoDTO parseEntityToDTO(BankAccount entity) {
		ContaBancariaInfoDTO cbDTO = new ContaBancariaInfoDTO();
		cbDTO.setCpf(entity.getCpf());
		cbDTO.setNome(entity.getNome());
		cbDTO.setId(entity.getId());
		cbDTO.setSaldo(validateBalance(entity));
		return cbDTO;
	}
	
	private static BigDecimal validateBalance(BankAccount entity) {
		return entity.getSaldo() != null? entity.getSaldo().setScale(2, RoundingMode.UP): BigDecimal.ZERO;
	}
	
	public static BankAccount parseDtoToEntity(ContaBancariaDTO cbDTO) {
		BankAccount cb = new BankAccount();
		cb.setNome(cbDTO.getNome());
		cb.setCpf(cbDTO.getCpf());
		return cb;
	}
}

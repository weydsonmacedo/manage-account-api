package br.com.donus.manageaccountapi.service;

import br.com.donus.manageaccountapi.dto.ContaBancariaInfoDTO;
import br.com.donus.manageaccountapi.dto.SaqueDTO;

public interface DrawService {
	public ContaBancariaInfoDTO draw(SaqueDTO saq);
}

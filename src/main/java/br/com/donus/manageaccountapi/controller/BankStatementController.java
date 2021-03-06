package br.com.donus.manageaccountapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.donus.manageaccountapi.dto.response.BankStatementDTO;
import br.com.donus.manageaccountapi.service.BankStatementService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bank-statement")
@RequiredArgsConstructor
public class BankStatementController {

	private final BankStatementService bankStatementService;
	
	@GetMapping(path = "/statement/{cpf}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<BankStatementDTO> statement( @PathVariable String cpf) {
		return new ResponseEntity<>(bankStatementService.getStatement(cpf), HttpStatus.OK);
	}

}

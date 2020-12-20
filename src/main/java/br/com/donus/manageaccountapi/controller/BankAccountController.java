package br.com.donus.manageaccountapi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.service.BankAccountService;


@RestController
@RequestMapping("/bank-account")
public class BankAccountController {

	private BankAccountService bankAccountService;
		
	
	public BankAccountController(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}

	@PostMapping(path = "/create")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<BankAccountInfoDTO> create( @RequestBody @Valid BankAccountDTO cbDTO) {
		return new ResponseEntity<>(bankAccountService.create(cbDTO), HttpStatus.CREATED);
	}
	

	@DeleteMapping(path = "/delete/{cpf}")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<String> delete( @PathVariable String cpf) {
		return new ResponseEntity<>(bankAccountService.delete(cpf), HttpStatus.OK);
	}

}

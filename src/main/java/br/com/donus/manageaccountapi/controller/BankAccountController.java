package br.com.donus.manageaccountapi.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.donus.manageaccountapi.dto.ContaBancariaDTO;
import br.com.donus.manageaccountapi.dto.DepositoDTO;
import br.com.donus.manageaccountapi.dto.SaqueDTO;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.DepositService;


@RestController
@RequestMapping("/conta-bancaria")
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired
	private DepositService depositService;
	
	@PostMapping(path = "/criar")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> criar( @RequestBody @Valid ContaBancariaDTO cbDTO) {
		return new ResponseEntity<>(bankAccountService.create(cbDTO), HttpStatus.CREATED);
	}

//	@RequestMapping(path = "/transferir", method = RequestMethod.POST)
//	public ResponseEntity<LocalDate> transferir() {
//		return ResponseEntity.ok(LocalDate.now());
//	}
//
	@PostMapping(path = "/depositar")
	public ResponseEntity<?> depositar(@RequestBody @Valid DepositoDTO dep) {
		return new ResponseEntity<>(depositService.deposit(dep), HttpStatus.OK);
	}

	@PostMapping(path = "/sacar")
	public ResponseEntity<?> sacar(@RequestBody @Valid SaqueDTO saq) {
		return ResponseEntity.ok(LocalDate.now());
	}
//
//	@RequestMapping(path = "/excluir", method = RequestMethod.DELETE)
//	public ResponseEntity<LocalDate> excluir() {
//		return ResponseEntity.ok(LocalDate.now());
//	}

}

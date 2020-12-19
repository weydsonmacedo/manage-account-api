package br.com.donus.manageaccountapi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.donus.manageaccountapi.dto.request.BankAccountDTO;
import br.com.donus.manageaccountapi.dto.request.DepositDTO;
import br.com.donus.manageaccountapi.dto.request.WithdrawDTO;
import br.com.donus.manageaccountapi.dto.response.BankAccountInfoDTO;
import br.com.donus.manageaccountapi.dto.response.ResponseTransactionInfoDTO;
import br.com.donus.manageaccountapi.service.BankAccountService;
import br.com.donus.manageaccountapi.service.DepositService;
import br.com.donus.manageaccountapi.service.WithdrawService;


@RestController
@RequestMapping("/bank-account")
public class BankAccountController {

	private BankAccountService bankAccountService;
	
	private DepositService depositService;
	
	private WithdrawService drawService;
	
	
	
	public BankAccountController(BankAccountService bankAccountService, DepositService depositService,
			WithdrawService drawService) {
		this.bankAccountService = bankAccountService;
		this.depositService = depositService;
		this.drawService = drawService;
	}

	@PostMapping(path = "/create")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<BankAccountInfoDTO> create( @RequestBody @Valid BankAccountDTO cbDTO) {
		return new ResponseEntity<>(bankAccountService.create(cbDTO), HttpStatus.CREATED);
	}

	@PostMapping(path = "/deposit")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseTransactionInfoDTO> deposit(@RequestBody @Valid DepositDTO dep) {
		return new ResponseEntity<>(depositService.deposit(dep), HttpStatus.OK);
	}

	@PostMapping(path = "/withdraw")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<ResponseTransactionInfoDTO> withdraw(@RequestBody @Valid WithdrawDTO saq) {
		return new ResponseEntity<>(drawService.draw(saq), HttpStatus.OK);
	}

}

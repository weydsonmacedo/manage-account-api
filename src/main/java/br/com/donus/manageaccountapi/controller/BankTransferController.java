package br.com.donus.manageaccountapi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.donus.manageaccountapi.dto.request.BankTransferDTO;
import br.com.donus.manageaccountapi.dto.response.TransferResponseDTO;
import br.com.donus.manageaccountapi.service.BankTransferService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/bank-transfer")
@RequiredArgsConstructor
public class BankTransferController {

	private final BankTransferService bankTransferService;

	@PostMapping(path = "/transfer")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<TransferResponseDTO> transfer( @RequestBody @Valid BankTransferDTO btDTO) {
		return new ResponseEntity<>(bankTransferService.transfer(btDTO), HttpStatus.OK);
	}


}

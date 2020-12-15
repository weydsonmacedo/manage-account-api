package br.com.donus.manageaccountapi.controller;

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
import br.com.donus.manageaccountapi.service.ContaBancariaService;


@RestController
@RequestMapping("/conta-bancaria")
public class ContaBancariaController {

	@Autowired
	private ContaBancariaService service;
	
	@PostMapping(path = "/criar")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> criar( @RequestBody @Valid ContaBancariaDTO cbDTO) {
		return new ResponseEntity<>(service.criar(cbDTO), HttpStatus.CREATED);
	}

//	@RequestMapping(name = "/transferir", method = RequestMethod.POST)
//	public ResponseEntity<LocalDate> transferir() {
//		return ResponseEntity.ok(LocalDate.now());
//	}
//
//	@RequestMapping(name = "/depositar", method = RequestMethod.POST)
//	public ResponseEntity<LocalDate> depositar() {
//		return ResponseEntity.ok(LocalDate.now());
//	}
//
//	@RequestMapping(name = "/sacar", method = RequestMethod.GET)
//	public ResponseEntity<LocalDate> sacar() {
//		return ResponseEntity.ok(LocalDate.now());
//	}
//
//	@RequestMapping(name = "/excluir", method = RequestMethod.DELETE)
//	public ResponseEntity<LocalDate> excluir() {
//		return ResponseEntity.ok(LocalDate.now());
//	}

}

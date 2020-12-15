package br.com.donus.manageaccountapi.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class Health {

	@GetMapping
	public ResponseEntity<LocalDate> health() {
		return ResponseEntity.ok(LocalDate.now());
	}
}

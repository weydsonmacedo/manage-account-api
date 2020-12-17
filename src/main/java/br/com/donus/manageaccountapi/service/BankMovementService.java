package br.com.donus.manageaccountapi.service;

import java.math.BigDecimal;

import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankMovement;
import br.com.donus.manageaccountapi.model.MovementType;

public interface BankMovementService {

	public BankMovement movement(BankAccount donor, BankAccount receiver, MovementType movementType, BigDecimal value );
	
	public BankMovement save(BankMovement bankMovement);
}

package br.com.donus.manageaccountapi.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.donus.manageaccountapi.model.BankAccount;
import br.com.donus.manageaccountapi.model.BankMovement;
import br.com.donus.manageaccountapi.model.MovementType;
import br.com.donus.manageaccountapi.repository.BankMovementRepository;
import br.com.donus.manageaccountapi.service.BankMovementService;

@Service
public class BankMovementServiceImpl implements BankMovementService {
	
	@Autowired
	BankMovementRepository bankMovementRepository;
	
	@Override
	public BankMovement movement(BankAccount donor, BankAccount receiver, MovementType movementType, BigDecimal value) {
		BankMovement bm = new BankMovement();
		bm.setDonor(donor);
		bm.setMovementType(movementType);
		bm.setReceiver(receiver);
		bm.setValue(value);
		bm.setCreationDate(LocalDateTime.now());
		return this.save(bm);
	}

	@Override
	public BankMovement save(BankMovement bankMovement) {
		return bankMovementRepository.save(bankMovement);
	}
	
	

}

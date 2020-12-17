package br.com.donus.manageaccountapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class BankStatement {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn
	private BankMovement bankMovement;
	
	@ManyToOne
	@JoinColumn
	private BankAccount bankAccount;
	
	@ColumnDefault("'0'")
	@PositiveOrZero
	@Column(nullable = false,updatable = true)
	private BigDecimal previousBalance;
	
	@ColumnDefault("'0'")
	@PositiveOrZero
	@Column(nullable = false,updatable = true)
	private BigDecimal currentBalance;
	
	private LocalDateTime creationDate;
	
	

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BankMovement getBankMovement() {
		return bankMovement;
	}

	public void setBankMovement(BankMovement bankMovement) {
		this.bankMovement = bankMovement;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public BigDecimal getPreviousBalance() {
		return previousBalance;
	}

	public void setPreviousBalance(BigDecimal previousBalance) {
		this.previousBalance = previousBalance;
	}

	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	
}

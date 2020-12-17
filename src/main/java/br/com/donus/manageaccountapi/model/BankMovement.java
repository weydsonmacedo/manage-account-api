package br.com.donus.manageaccountapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.Min;

@Entity
public class BankMovement {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	

	@Column(name = "TRANSACTION_ID",updatable = false, unique = true)
	private UUID transactionId;
	
	@ManyToOne
	@JoinColumn
	private BankAccount donor;
	
	@Enumerated(EnumType.STRING)
	private MovementType movementType;

	@ManyToOne
	@JoinColumn
	private BankAccount receiver;
	
	@Min(1)
	private BigDecimal value;
	
	private LocalDateTime creationDate;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId != null ? transactionId.toString(): null;
	}

	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}

	public BankAccount getDonor() {
		return donor;
	}

	public void setDonor(BankAccount donor) {
		this.donor = donor;
	}

	public MovementType getMovementType() {
		return movementType;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}

	public BankAccount getReceiver() {
		return receiver;
	}

	public void setReceiver(BankAccount receiver) {
		this.receiver = receiver;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	@PrePersist
    protected void onCreate() {
		setTransactionId(java.util.UUID.randomUUID());
    }
	
}

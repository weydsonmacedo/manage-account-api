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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class BankTransaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	

	@Column(name = "TRANSACTION_ID",updatable = false, unique = true)
	private UUID transactionCode;
	
	@ManyToOne
	@JoinColumn
	private BankAccount donor;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;

	@ManyToOne
	@JoinColumn
	private BankAccount receiver;
	
	@Min(1)
	private BigDecimal value;
	
	@ColumnDefault("'0'")
	@PositiveOrZero
	private BigDecimal bonification;
	
	@ColumnDefault("'0'")
	@PositiveOrZero
	private BigDecimal fee;
	
	@NotNull
	private LocalDateTime creationDate;
	

	public BigDecimal getBonification() {
		return bonification;
	}

	public void setBonification(BigDecimal bonification) {
		this.bonification = bonification;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionCode() {
		return transactionCode != null ? transactionCode.toString(): null;
	}

	public void setTransactionCode(UUID transactionCode) {
		this.transactionCode = transactionCode;
	}

	public BankAccount getDonor() {
		return donor;
	}

	public void setDonor(BankAccount donor) {
		this.donor = donor;
	}


	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
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
		setTransactionCode(java.util.UUID.randomUUID());
    }
	
}

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	


	public String getTransactionCode() {
		return transactionCode != null ? transactionCode.toString(): null;
	}

	
	@PrePersist
    protected void onCreate() {
		setTransactionCode(java.util.UUID.randomUUID());
    }
	
}

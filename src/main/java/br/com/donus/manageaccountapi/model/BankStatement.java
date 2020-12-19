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
public class BankStatement {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@ManyToOne(optional = false)
	@JoinColumn
	private BankTransaction bankTransaction;
	
	@ManyToOne(optional = false)
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
	
	@NotNull
	private LocalDateTime creationDate;
	
	
}

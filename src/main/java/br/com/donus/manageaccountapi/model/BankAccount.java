package br.com.donus.manageaccountapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BankAccount {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min=2, max=150)
	private String name;
	
	@NotNull
	@Column(unique = true)
	@CPF
	private String cpf;
	
	@ColumnDefault("'0'")
	@PositiveOrZero
	@Column(insertable = false,nullable = false,updatable = true)
	private BigDecimal balance;
	
	@NotNull
	private LocalDateTime creationDate;
	
	private LocalDateTime modifiedDate;
	

}

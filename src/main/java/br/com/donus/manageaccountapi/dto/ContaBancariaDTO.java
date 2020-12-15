package br.com.donus.manageaccountapi.dto;

import javax.validation.constraints.NotBlank;

import lombok.ToString;


@ToString
public class ContaBancariaDTO {

	private long id;
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}

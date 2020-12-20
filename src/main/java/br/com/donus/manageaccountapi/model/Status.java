package br.com.donus.manageaccountapi.model;

public enum Status {

	ACTIVE("ATIVO"),
	INACTIVE("INATIVO");
	private String status;

	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	
}

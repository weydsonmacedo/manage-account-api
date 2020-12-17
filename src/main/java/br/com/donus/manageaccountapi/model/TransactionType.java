package br.com.donus.manageaccountapi.model;

public enum TransactionType {

	WITHDRAW("WITHDRAW"),
	TRANSFER("TRANSFER"),
	DEPOSIT("DEPOSIT");

	private String movementType;
	
	TransactionType(String movementType) {
		this.movementType = movementType;
		
	}

	public String getMovementType() {
		return movementType;
	}
	
	
}

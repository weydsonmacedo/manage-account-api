package br.com.donus.manageaccountapi.model;

public enum MovementType {

	WITHDRAW("WITHDRAW"),
	TRANSFER("TRANSFER"),
	DEPOSIT("DEPOSIT");

	private String movementType;
	
	MovementType(String movementType) {
		this.movementType = movementType;
		
	}

	public String getMovementType() {
		return movementType;
	}
	
	
}

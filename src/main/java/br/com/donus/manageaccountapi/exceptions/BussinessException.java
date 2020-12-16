package br.com.donus.manageaccountapi.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BussinessException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3250146071456121454L;

	private HttpStatus status;
	public BussinessException(HttpStatus status,String message) {
		super(message);
		this.status = status;
	}

	/**
	 * BAD_REQUEST BY DEFAULT
	 * @param message
	 */
	public BussinessException(String message) {
		super(message);
		this.status = HttpStatus.BAD_REQUEST;
	}

	
}

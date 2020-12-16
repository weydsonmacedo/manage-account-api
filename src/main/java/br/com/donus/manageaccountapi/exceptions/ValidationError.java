package br.com.donus.manageaccountapi.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationError extends MessageError{

	 private final String field;
	 private final String fieldMessage;
	
	
}

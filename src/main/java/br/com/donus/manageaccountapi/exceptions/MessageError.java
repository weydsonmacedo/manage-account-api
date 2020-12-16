package br.com.donus.manageaccountapi.exceptions;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Data
public class MessageError {

	private String title;
	private int status;
	private LocalDateTime timestamp;
	private String detail;
	private String developerMessage;

}

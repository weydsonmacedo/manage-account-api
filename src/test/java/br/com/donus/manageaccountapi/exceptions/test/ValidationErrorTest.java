package br.com.donus.manageaccountapi.exceptions.test;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.exceptions.ValidationError;

@DisplayName("Tests for ValidationErrorTest  class ")
@ExtendWith(SpringExtension.class)
class ValidationErrorTest {

	@Test
	void testValidationError() {
		ValidationError message = ValidationError.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("ARGUMENTO INVÁLIDO")
				.developerMessage("")
				.field("")
				.fieldMessage("")
				.detail("")
				.build();

		
		ValidationError msg = message;
		Assertions.assertNotNull(message.getDetail());
		Assertions.assertNotNull(message.getDeveloperMessage());
		Assertions.assertNotNull(message.getField());
		Assertions.assertNotNull(message.getFieldMessage());
		Assertions.assertNotNull(message.getStatus());
		Assertions.assertNotNull(message.getTimestamp());
		Assertions.assertNotNull(message.getTitle());
		Assertions.assertNotNull(message);
		Assertions.assertNotNull(message.toString());
		Assertions.assertEquals(message, msg);
	}

	@Test
	private void toStringTest() {
	String msg =	ValidationError.builder()
		.timestamp(LocalDateTime.now())
		.status(HttpStatus.BAD_REQUEST.value())
		.title("ARGUMENTO INVÁLIDO")
		.developerMessage("")
		.field("")
		.fieldMessage("")
		.detail("")
		.toString();
	Assertions.assertNotNull(msg);
	}

}

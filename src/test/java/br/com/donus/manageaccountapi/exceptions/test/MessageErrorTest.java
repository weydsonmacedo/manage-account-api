package br.com.donus.manageaccountapi.exceptions.test;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.donus.manageaccountapi.exceptions.MessageError;

@DisplayName("Tests for MessageErrorTest  class ")
@ExtendWith(SpringExtension.class)
class MessageErrorTest {

	@Test
	void testMessageError() {
		MessageError message = MessageError.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value())
				.title("RECURSO NÃO ENCONTRADO")
				.detail("")
				.developerMessage("").build();


		MessageError msg = message;
		message.setDetail("");
		message.setDeveloperMessage("");
		message.setStatus(HttpStatus.NOT_FOUND.value());
		message.setTimestamp(LocalDateTime.now());
		message.setTitle("");

		
		message.equals(msg);
		Assertions.assertNotNull(message.hashCode());
		Assertions.assertNotNull(message);
		Assertions.assertNotNull(message.getDetail());
		Assertions.assertNotNull(message.getDeveloperMessage());
		Assertions.assertNotNull(message);
		Assertions.assertNotNull(message.getStatus());
		Assertions.assertNotNull(message.getTimestamp());
		Assertions.assertNotNull(message.getTitle());
		Assertions.assertNotNull(message);
		Assertions.assertNotNull(message.toString());
		
	}

	@Test
	private void toStringTest() {
	String msg = MessageError.builder()
		.timestamp(LocalDateTime.now())
		.status(HttpStatus.NOT_FOUND.value())
		.title("RECURSO NÃO ENCONTRADO")
		.detail("")
		.developerMessage("").toString();
		
		Assertions.assertNotNull(msg);
	}

}

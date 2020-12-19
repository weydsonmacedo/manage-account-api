package br.com.donus.manageaccountapi.config.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import br.com.donus.manageaccountapi.config.RestExceptionHandler;
import br.com.donus.manageaccountapi.exceptions.BussinessException;
import util.MockClasses;

@DisplayName("Tests for Exception handler class ")
@ExtendWith(SpringExtension.class)
class RestExceptionHandlerTest {

	@InjectMocks
	private RestExceptionHandler handler;
	
	@Test
	void testHandleMethodArgumentNotValid() throws NoSuchMethodException, SecurityException {
		
		BeanPropertyBindingResult errors = new BeanPropertyBindingResult(MockClasses.getBankAccount(), "bankAccount");
		errors.rejectValue("name", "invalid");
		MethodParameter parameter = new MethodParameter(this.getClass().getMethod("handle", String.class), 0);
		MethodArgumentNotValidException ex = new MethodArgumentNotValidException(parameter, errors);
		
		ResponseEntity<Object> handleMethodArgumentNotValid = handler.handleMethodArgumentNotValid(ex, null, null, null);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, handleMethodArgumentNotValid.getStatusCode());
	}

	@Test
	void testHandleResourceNotFoundException() {
		ResponseEntity<Object> handleResourceNotFoundException = handler.handleResourceNotFoundException(new ResourceNotFoundException("teste"));
		Assertions.assertEquals(HttpStatus.NOT_FOUND, handleResourceNotFoundException.getStatusCode());
	}

	@Test
	void testHandleDataIntegrityViolationException() {
		ResponseEntity<Object> handleDataIntegrityViolationException = handler.handleDataIntegrityViolationException( new DataIntegrityViolationException("teste"));
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, handleDataIntegrityViolationException.getStatusCode());
	}

	@Test
	void testHandleNullPointerException() {
		ResponseEntity<Object> handleNullPointerException = handler.handleNullPointerException(new NullPointerException("null"));
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, handleNullPointerException.getStatusCode());
	}

	@Test
	void testHandleBussinessException() {
		ResponseEntity<Object> handleBussinessException = handler.handleBussinessException(new BussinessException("bussiness exception"));
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, handleBussinessException.getStatusCode());
	}

	@Test
	void testHandleConstraintViolationException() {
		ResponseEntity<Object> handleConstraintViolationException = handler.handleConstraintViolationException(new TransactionSystemException("constraint violation"));
		Assertions.assertEquals(HttpStatus.CONFLICT, handleConstraintViolationException.getStatusCode());
	}
	
	@SuppressWarnings("unused")
	public void handle(String arg) {
	}

}

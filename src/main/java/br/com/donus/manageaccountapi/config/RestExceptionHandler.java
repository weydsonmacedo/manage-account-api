package br.com.donus.manageaccountapi.config;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.donus.manageaccountapi.exceptions.BussinessException;
import br.com.donus.manageaccountapi.exceptions.MessageError;
import br.com.donus.manageaccountapi.exceptions.ValidationError;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, 
			HttpHeaders headers, 
			HttpStatus status, 
			WebRequest request) {
		logger.error("EXCEÇÃO DE NEGÓCIO: ",ex);
		List<FieldError> fieldErros =  ex.getBindingResult().getFieldErrors();
		String fields = fieldErros.stream().map(FieldError :: getField).collect(Collectors.joining(","));
		String messages = fieldErros.stream().map(FieldError :: getDefaultMessage).collect(Collectors.joining(","));
		ValidationError message = ValidationError.builder()
			.timestamp(LocalDateTime.now())
			.status(HttpStatus.BAD_REQUEST.value())
			.title("ARGUMENTO INVÁLIDO")
			.developerMessage(ex.getClass().getName())
			.field(fields)
			.fieldMessage(messages)
			.detail(ex.getMessage())
			.build();
		
		
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
		logger.error("EXCEÇÃO DE NEGÓCIO: ",ex);
		MessageError message = MessageError.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.NOT_FOUND.value())
				.title("RECURSO NÃO ENCONTRADO")
				.detail(ex.getMessage())
				.developerMessage(ex.getClass().getName()).build();
				
			
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		logger.error("VIOLAÇÃO DE INTEGRIDADE DE DADOS ENCONTRADA: ",ex);
	
		MessageError message = MessageError.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("VIOLAÇÃO DE INTEGRIDADE DE DADOS ENCONTRADA")
				.detail(ex.getMessage())
				.developerMessage(ex.getClass().getName()).build();
				
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
		logger.error("NULLPOINTER - LOG DE ERRO: ",ex);
		
		MessageError message = MessageError.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("OCORREU UM ERRO INESPERADO")
				.detail(ex.getMessage())
				.developerMessage(ex.getClass().getName()).build();
				
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BussinessException.class)
	public ResponseEntity<Object> handleBussinessException(BussinessException ex) {
		logger.error("EXCEÇÃO DE NEGÓCIO: ",ex);
		
		MessageError message = MessageError.builder()
				.timestamp(LocalDateTime.now())
				.status(ex.getStatus().value())
				.title("EXCEÇÃO DE NEGÓCIO")
				.detail(ex.getMessage())
				.developerMessage(ex.getClass().getName()).build();
				
			return new ResponseEntity<>(message, ex.getStatus());
	}
	
	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<Object> handleConstraintViolationException(
			TransactionSystemException ex) {
		
		logger.error("EXCEÇÃO DE TRANSAÇÃO: ",ex);		

		MessageError message = MessageError.builder()
				.timestamp(LocalDateTime.now())
				.status(HttpStatus.CONFLICT.value())
				.title("OCORREU UM ERRO INESPERADO - VIOLAÇÃO DE RESTRIÇÃO")
				.detail(ex.getMessage())
				.developerMessage(ex.getClass().getName()).build();
		return new ResponseEntity<>(message, HttpStatus.CONFLICT);
	}
}

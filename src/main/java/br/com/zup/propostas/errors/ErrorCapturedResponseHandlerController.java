package br.com.zup.propostas.errors;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TransactionRequiredException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException;
@RestControllerAdvice
public class ErrorCapturedResponseHandlerController {
	
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)	
	public ResponseEntity<List<ErrorCapturedResponseHander>> responseMethodArgumentNotValidException(MethodArgumentNotValidException errors){
		
		List<ErrorCapturedResponseHander> errorsCaptured = new ArrayList<>();

		for(FieldError fieldError :errors.getBindingResult().getFieldErrors()) {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			
			errorsCaptured.add(new ErrorCapturedResponseHander(fieldError.getField(), message));
		}
			
		return ResponseEntity.badRequest().body(errorsCaptured);
	}

	@ExceptionHandler(IllegalStateException.class)	
	public ResponseEntity<List<ErrorCapturedResponseHander>> responseIllegalStateException(IllegalStateException error){
		
		List<ErrorCapturedResponseHander> errorsCaptured = new ArrayList<>();

		
		 errorsCaptured.add(new ErrorCapturedResponseHander("illegal state exception", error.getLocalizedMessage()));
		
			
		return ResponseEntity.badRequest().body(errorsCaptured);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<ErrorCapturedResponseHander>> responseConstraintViolationException(ConstraintViolationException error){

		List<ErrorCapturedResponseHander> errorsCaptured = new ArrayList<>();


		errorsCaptured.add(new ErrorCapturedResponseHander("Error Cartão:", error.getLocalizedMessage()));


		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorsCaptured);
	}

	@ExceptionHandler(TransactionRequiredException.class)	
	public ResponseEntity<List<ErrorCapturedResponseHander>> responseTransactionRequiredException(TransactionRequiredException error){
		
		List<ErrorCapturedResponseHander> errorsCaptured = new ArrayList<>();

		
		 errorsCaptured.add(new ErrorCapturedResponseHander("transaction required exception", error.getLocalizedMessage()));
		
			
		return ResponseEntity.badRequest().body(errorsCaptured);
	}

}
	


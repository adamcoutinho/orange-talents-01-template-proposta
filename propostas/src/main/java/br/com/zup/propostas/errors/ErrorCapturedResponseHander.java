package br.com.zup.propostas.errors;

import java.time.LocalDateTime;

public class ErrorCapturedResponseHander {
	
	
	private LocalDateTime instant = LocalDateTime.now();
	
	private String field;
	
	private String message;

	public ErrorCapturedResponseHander(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public LocalDateTime getInstant() {
		return instant;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}
	
	

}

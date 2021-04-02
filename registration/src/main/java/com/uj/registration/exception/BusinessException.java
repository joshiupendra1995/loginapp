package com.uj.registration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(value=HttpStatus.SERVICE_UNAVAILABLE) 
public class BusinessException extends Exception {

	private static final long serialVersionUID = 6134467581472827549L;

	private String message;

	public BusinessException(String message) {
		super(message);
	}

}

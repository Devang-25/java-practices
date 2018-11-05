package com.preety.rest.webservices.fileapis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class FileStorageException extends RuntimeException {
	
	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Exception ex) {
		super(message + ex);
	}

}

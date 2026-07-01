package com.ecommerce.ecomm_user_service.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(UserAlreadyExistsException.class)
	    public ResponseEntity<?> handleUserExists(
	            UserAlreadyExistsException ex) {

	        return ResponseEntity
	                .status(HttpStatus.CONFLICT)
	                .body(new ErrorResponse(409, "Conflict",
							ex.getMessage(), LocalDateTime.now()
	                ));
	    }

	    @ExceptionHandler(InvalidCredentialsException.class)
	    public ResponseEntity<?> handleInvalidCredentials(
	            InvalidCredentialsException ex) {

	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of(
	                        "timestamp", LocalDateTime.now(),
	                        "message", ex.getMessage()
	                ));
	    }
}

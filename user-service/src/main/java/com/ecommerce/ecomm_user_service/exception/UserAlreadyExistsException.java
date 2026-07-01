package com.ecommerce.ecomm_user_service.exception;

public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException(String message) {
        super(message);
    }
}

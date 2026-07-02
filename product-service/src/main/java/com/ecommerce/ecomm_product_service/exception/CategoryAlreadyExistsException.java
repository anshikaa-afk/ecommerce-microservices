package com.ecommerce.ecomm_product_service.exception;

public class CategoryAlreadyExistsException extends RuntimeException{
    public CategoryAlreadyExistsException(String message){
        super(message);
    }
}

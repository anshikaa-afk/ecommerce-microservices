package com.ecommerce.ecomm_user_service.dto;

public record UserResponse(
        Long id,
        String name,
        String email,
        String role
) {
}

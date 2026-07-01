package com.ecommerce.ecomm_user_service.dto;

import jakarta.validation.constraints.*;

public record LoginRequest(
		@Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password
		) {

}

package com.ecommerce.ecomm_user_service.controller;

import com.ecommerce.ecomm_user_service.dto.AuthResponse;
import com.ecommerce.ecomm_user_service.dto.LoginRequest;
import com.ecommerce.ecomm_user_service.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecomm_user_service.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
//	 "name": "Anshika",
//			 "email": "saxena.a@mail.com",
//			 "password": "abc344
	@PostMapping("/register")
	public ResponseEntity<String> register(
			@Valid
			@RequestBody
			RegisterRequest request) {
		String response = authService.register(request);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody
	                                          LoginRequest request){
		return ResponseEntity.ok(authService.login(request));
	}
}

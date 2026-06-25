package com.ecommerce.ecomm_user_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecomm_user_service.dto.RegisterRequest;
import com.ecommerce.ecomm_user_service.entity.Role;
import com.ecommerce.ecomm_user_service.entity.User;
import com.ecommerce.ecomm_user_service.exception.UserAlreadyExistsException;
import com.ecommerce.ecomm_user_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	 public AuthService(
	            UserRepository userRepository,
	            PasswordEncoder passwordEncoder) {

	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }
	 
	public String register(RegisterRequest request) {
		if(userRepository.findByEmail(request.email()).isPresent()) {
			throw new UserAlreadyExistsException("User already exists");
		}
		
		User user = new User();

		user.setName(request.name());
		user.setEmail(request.email());
		user.setPassword(
		        passwordEncoder.encode(
		                request.password()
		        )
		);
		user.setRole(Role.USER);
		
		userRepository.save(user);
		return "User Registered Succesfully";
	}
	
}

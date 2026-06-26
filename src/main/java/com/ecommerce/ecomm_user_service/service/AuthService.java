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

		User user = User.builder()
				.name(request.name())
				.email(request.email())
				.password(request.password())
				.role(Role.USER)
				.build();
		
		userRepository.save(user);
		return "User Registered Succesfully";
	}
	
}

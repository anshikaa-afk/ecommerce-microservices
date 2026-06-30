package com.ecommerce.ecomm_user_service.service;

import com.ecommerce.ecomm_user_service.dto.AuthResponse;
import com.ecommerce.ecomm_user_service.dto.LoginRequest;
import com.ecommerce.ecomm_user_service.exception.InvalidCredentialsException;
import com.ecommerce.ecomm_user_service.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecomm_user_service.dto.RegisterRequest;
import com.ecommerce.ecomm_user_service.entity.Role;
import com.ecommerce.ecomm_user_service.entity.User;
import com.ecommerce.ecomm_user_service.exception.UserAlreadyExistsException;
import com.ecommerce.ecomm_user_service.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
public class AuthService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	 public AuthService(
             UserRepository userRepository,
             PasswordEncoder passwordEncoder,
			 AuthenticationManager authenticationManager, JwtService jwtService) {

		 this.userRepository = userRepository;
		 this.passwordEncoder = passwordEncoder;
         this.authenticationManager = authenticationManager;
         this.jwtService = jwtService;
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

	public AuthResponse login(LoginRequest request) {

		 authenticationManager.authenticate(
				 new UsernamePasswordAuthenticationToken(
						 request.email(), request.password()));

		 User user = userRepository
				 .findByEmail(request.email())
				 .orElseThrow(() -> new InvalidCredentialsException(
						 "Invalid Credentials"));

		 String token = jwtService.generateToke(
				 new org.springframework.security.core.userdetails.User(
						 user.getEmail(), user.getPassword(),
						 List.of(new SimpleGrantedAuthority(
								 "ROLE_"+user.getRole().name()
						 ))
				 ));
		 return  new AuthResponse(token);
	}
	
}

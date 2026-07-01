package com.ecommerce.ecomm_user_service.controller;

import com.ecommerce.ecomm_user_service.dto.UserResponse;
import com.ecommerce.ecomm_user_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;

    @GetMapping("/me")
    public UserResponse me(Authentication authentication){
        return authService.getCurrentUser(authentication.getName());
    }
}

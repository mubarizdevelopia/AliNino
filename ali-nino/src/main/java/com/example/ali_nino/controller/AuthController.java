package com.example.ali_nino.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ali_nino.dao.entity.User;
import com.example.ali_nino.dao.repository.UserRepository;
import com.example.ali_nino.model.AuthResponse;
import com.example.ali_nino.model.LoginRequestDto;
import com.example.ali_nino.model.RegisterRequestDto;
import com.example.ali_nino.security.JwtUtils;
import com.example.ali_nino.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService; 
    
    public AuthController(AuthService authService) {
    	this.authService = authService;
    }
    
    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequestDto registerRequest) {
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse authenticateUser(@RequestBody LoginRequestDto loginRequest) {
        return authService.loginUser(loginRequest);
    }
}

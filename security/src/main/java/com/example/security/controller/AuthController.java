package com.example.security.controller;

import com.example.security.dto.RegisterRequest;
import com.example.security.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Spring Security Project Working!";
    }
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

}
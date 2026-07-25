package com.example.security.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController {

    @GetMapping("/public")
    public String publicApi() {
        return "This is Public";
    }

    @GetMapping("/user")
    public String userApi() {
        return "Welcome USER";
    }

    @GetMapping("/admin")
    public String adminApi() {
        return "Welcome ADMIN";
    }
}
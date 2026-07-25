package com.example.security.service;

import com.example.security.dto.RegisterRequest;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository,
                       PasswordEncoder encoder) {

        this.repository = repository;
        this.encoder = encoder;
    }

    public String register(RegisterRequest request) {

        if(repository.findByUsername(request.getUsername()).isPresent()){
            return "Username already exists";
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(user);

        return "User Registered Successfully";
    }
}
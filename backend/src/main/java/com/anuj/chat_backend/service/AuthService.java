package com.anuj.chat_backend.service;

import com.anuj.chat_backend.dto.RegisterRequest;
import com.anuj.chat_backend.dto.UserResponse;
import com.anuj.chat_backend.entity.User;
import com.anuj.chat_backend.exception.EmailAlreadyExistsException;
import com.anuj.chat_backend.exception.UsernameAlreadyExistsException;
import com.anuj.chat_backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.fromUser(savedUser);
    }
}
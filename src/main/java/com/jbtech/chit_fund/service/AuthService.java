package com.jbtech.chit_fund.service;


import com.jbtech.chit_fund.dto.LoginRequest;
import com.jbtech.chit_fund.dto.RegisterRequest;
import com.jbtech.chit_fund.model.User;
import com.jbtech.chit_fund.repository.UserRepository;
import com.jbtech.chit_fund.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;

    // ── REGISTER ──────────────────────────────────────────
    public Map<String, String> register(RegisterRequest request) {

        // Check duplicates
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Create and save user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // 🔒 Hash it!
        user.setRole(request.getRole());
        user.setIsActive("Y");

        userRepository.save(user);

        // Return token immediately after registration
        String token = jwtUtil.generateToken(user.getUsername());
        return Map.of(
                "token", token,
                "message", "Registration successful"
        );
    }

    // ── LOGIN ─────────────────────────────────────────────
    public Map<String, String> login(LoginRequest request) {

        // This internally calls CustomUserDetailsService.loadUserByUsername()
        // and compares BCrypt password — throws exception if invalid
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword())
        );

        // Fetch user details from DB for response
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String token = jwtUtil.generateToken(user.getUsername());

        return Map.of(
                "token", token,
                "username", user.getUsername(),
                "email", user.getEmail(),
                "role", user.getRole()
        );
    }
}
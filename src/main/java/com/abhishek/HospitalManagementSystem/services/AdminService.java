package com.abhishek.HospitalManagementSystem.services;

import ch.qos.logback.classic.Logger;
import com.abhishek.HospitalManagementSystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword; // BCrypt-hashed

    // Authenticate admin and generate JWT
    public String authenticateAndGenerateToken(String email, String rawPassword) {
        if (!email.equals(adminEmail) || !passwordEncoder.matches(rawPassword, adminPassword)) {

            throw new RuntimeException("Invalid admin credentials");
        }
        // Use UserDetails type
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(adminEmail)
                .password(adminPassword)
                .authorities("ROLE_ADMIN")
                .build();

        // Generate JWT
        return jwtUtil.generateToken(userDetails);
    }
}

package com.abhishek.HospitalManagementSystem.services;

import com.abhishek.HospitalManagementSystem.models.User;
import com.abhishek.HospitalManagementSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from DB by email
        if (username.equals(adminEmail)) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(adminEmail)
                    .password(adminPassword) // bcrypt hash of admin123
                    .authorities("ROLE_ADMIN")
                    .build();
        }
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        // Map your entity User → Spring Security UserDetails
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())              // login using email
                .password(user.getPassword())           // BCrypt encoded password
                .authorities(user.getRole())            // e.g., ROLE_PATIENT, ROLE_DOCTOR, ROLE_ADMIN
                .disabled(!user.isEnabled())            // handle active/inactive users
                .build();
    }
}

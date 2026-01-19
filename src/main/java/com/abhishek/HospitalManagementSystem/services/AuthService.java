package com.abhishek.HospitalManagementSystem.services;

import com.abhishek.HospitalManagementSystem.dto.RegisterRequest;
import com.abhishek.HospitalManagementSystem.dto.LoginRequest;
import com.abhishek.HospitalManagementSystem.models.*;
import com.abhishek.HospitalManagementSystem.repositories.*;
import com.abhishek.HospitalManagementSystem.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User.UserBuilder userBuilder = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().toUpperCase());

        if ("PATIENT".equalsIgnoreCase(request.getRole())) {
            Patient patient = Patient.builder()
                    .name(request.getPatientName())
                    .gender(request.getGender())
                    .age(request.getAge())
                    .build();
            userBuilder.patient(patient);
            userBuilder.enabled(true); // ✅ Patients are active immediately
        }
        else if ("DOCTOR".equalsIgnoreCase(request.getRole())) {
            Doctor doctor = Doctor.builder()
                    .name(request.getDoctorName())
                    .experience(request.getExperience())
                    .speciality(request.getSpeciality())
                    .build();
            userBuilder.doctor(doctor);
            userBuilder.enabled(false); // ❌ Doctors need admin approval
        }
        else {
            throw new RuntimeException("Invalid role: " + request.getRole());
        }

        User savedUser = userRepository.save(userBuilder.build());

        if ("DOCTOR".equalsIgnoreCase(savedUser.getRole())) {
            return "Doctor registered successfully. Awaiting admin verification.";
        }

        return jwtUtil.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(savedUser.getEmail())
                        .password(savedUser.getPassword())
                        .authorities(savedUser.getRole())
                        .build()
        );
    }

    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.isEnabled()) {
            throw new RuntimeException("Account is not verified by admin yet");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return jwtUtil.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .authorities(user.getRole())
                        .build()
        );
    }
}

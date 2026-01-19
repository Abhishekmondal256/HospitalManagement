package com.abhishek.HospitalManagementSystem.controllers;

import com.abhishek.HospitalManagementSystem.services.AdminService;
import com.abhishek.HospitalManagementSystem.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final UserRepository userRepository;

    // 🔑 Admin Login -> Returns JWT
    @PostMapping("/login")
    public String adminLogin(@RequestParam String email, @RequestParam String password) {

        return adminService.authenticateAndGenerateToken(email, password);
    }

    // ✅ Verify doctor (requires admin JWT)
    @PutMapping("/verify-doctor/{id}")
    public String verifyDoctor(@PathVariable Long id) {
        var doctor = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (!"DOCTOR".equalsIgnoreCase(doctor.getRole())) {
            return "Not a doctor account";
        }

        doctor.setEnabled(true);
        userRepository.save(doctor);

        return "Doctor " + doctor.getEmail() + " verified successfully.";
    }
}

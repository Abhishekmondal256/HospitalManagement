package com.abhishek.HospitalManagementSystem.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // login identifier
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // store BCrypt-hashed password

    // Role: PATIENT / DOCTOR / ADMIN
    @Column(nullable = false)
    private String role;

    // ✅ One-to-One mappings with Doctor & Patient
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id", referencedColumnName = "id", unique = true)
    private Patient patient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", unique = true)
    private Doctor doctor;

    private boolean enabled = true; // for account activation
}

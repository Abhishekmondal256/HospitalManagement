package com.abhishek.HospitalManagementSystem.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String email;
    private String password;
    private String role; // PATIENT / DOCTOR

    // patient details
    private String patientName;
    private String gender;
    private int age;

    // doctor details
    private String doctorName;
    private int experience;
    private String speciality;
}

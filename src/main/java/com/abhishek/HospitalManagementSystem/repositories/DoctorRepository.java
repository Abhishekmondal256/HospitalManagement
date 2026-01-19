package com.abhishek.HospitalManagementSystem.repositories;

import com.abhishek.HospitalManagementSystem.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}

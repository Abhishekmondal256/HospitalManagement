package com.abhishek.HospitalManagementSystem.repositories;

import com.abhishek.HospitalManagementSystem.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

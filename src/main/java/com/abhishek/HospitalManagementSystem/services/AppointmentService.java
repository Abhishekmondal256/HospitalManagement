package com.abhishek.HospitalManagementSystem.services;

import com.abhishek.HospitalManagementSystem.models.Appointment;
import com.abhishek.HospitalManagementSystem.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id " + id));
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Appointment existing = getAppointmentById(id);
        existing.setPatientId(updatedAppointment.getPatientId());
        existing.setDoctorId(updatedAppointment.getDoctorId());
        existing.setDate(updatedAppointment.getDate());
        return appointmentRepository.save(existing);
    }
}

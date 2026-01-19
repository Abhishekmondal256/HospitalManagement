package com.abhishek.HospitalManagementSystem.services;

import com.abhishek.HospitalManagementSystem.models.Patient;
import com.abhishek.HospitalManagementSystem.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Page<Patient> getAllPatients(int page, int size) {
        Pageable pageable= PageRequest.of(page,size);
        return patientRepository.findAll(pageable);
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + id));
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existing = getPatientById(id);
        existing.setName(updatedPatient.getName());
        existing.setAge(updatedPatient.getAge());
        existing.setGender(updatedPatient.getGender());
        return patientRepository.save(existing);
    }
}

package org.example.patientmanagement.service;

import org.example.patientmanagement.model.Patient;
import org.example.patientmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.getPatientById(id);
    }

    public List<Patient> getPatientsByLastName(String lastName) {
        return patientRepository.findByLastName(lastName);
    }
}

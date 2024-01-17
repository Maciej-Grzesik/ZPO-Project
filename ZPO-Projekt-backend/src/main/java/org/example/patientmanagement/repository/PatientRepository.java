package org.example.patientmanagement.repository;

import org.example.patientmanagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
    List<Patient> getAllPatients();
    Optional<Patient> getPatientById(Long id);
    List<Patient> findByLastName(String lastName);
}

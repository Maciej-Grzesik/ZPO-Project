package org.example.patientmanagement.repository;

import org.example.patientmanagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repozytorium `PatientRepository` do obsługi operacji na danych pacjentów w bazie danych
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Pobiera pacjenta o określonym identyfikatorze
     *
     * @param id Identyfikator pacjenta
     * @return Optional zawierający pacjenta lub pusty, jeśli pacjent o danym ID nie istnieje
     */
    Optional<Patient> getPatientById(Long id);

    /**
     * Pobiera listę pacjentów o określonym nazwisku
     *
     * @param lastName Nazwisko pacjenta
     * @return Lista pacjentów o danym nazwisku
     */
    List<Patient> findByLastName(String lastName);
}
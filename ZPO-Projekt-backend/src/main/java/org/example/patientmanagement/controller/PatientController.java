package org.example.patientmanagement.controller;

import org.example.patientmanagement.exceptions.PatientNotFoundException;
import org.example.patientmanagement.model.Patient;
import org.example.patientmanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://zpo-project.s3-website.eu-north-1.amazonaws.com/", methods = {RequestMethod.GET, RequestMethod.DELETE},
        allowedHeaders = {"Content-Type", "Authorization"},
        exposedHeaders = {"Content-Type", "Authorization"})
public class PatientController<T extends Patient> {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients/all/json")
    public ResponseEntity<List<Patient>> getAllPatientsJson() {
        List<Patient> patients = patientRepository.findAll();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/patients/adults/json")
    public ResponseEntity<List<Patient>> getAdultPatientsJson() {
        List<Patient> adultPatients = patientRepository.findAll()
                .stream()
                .filter(patient -> patient.getAge() >= 18 && patient.getAge() <= 65)
                .collect(Collectors.toList());

        return ResponseEntity.ok(adultPatients);
    }

    @GetMapping("/patients/children/json")
    public ResponseEntity<List<Patient>> getChildPatientsJson() {
        List<Patient> childPatients = patientRepository.findAll()
                .stream()
                .filter(patient -> patient.getAge() < 18)
                .collect(Collectors.toList());
        return ResponseEntity.ok(childPatients);
    }

    @GetMapping("/patients/seniors/json")
    public ResponseEntity<List<Patient>> getSeniorPatientsJson() {
        List<Patient> seniorPatients = patientRepository.findAll()
                .stream()
                .filter(patient -> patient.getAge() > 65)
                .collect(Collectors.toList());

        return ResponseEntity.ok(seniorPatients);
    }


    @DeleteMapping("/patients/json/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable long id){
        try {
            Patient patient = patientRepository.findById(id)
                    .orElseThrow(() -> new PatientNotFoundException("Theres no patient under id: " + id));

            patientRepository.delete(patient);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(createErrorObject(e.getMessage(), HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorObject("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    private Map<String, Object> createErrorObject(String message, HttpStatus status) {
        Map<String, Object> errorObject = new HashMap<>();
        errorObject.put("error", message);
        errorObject.put("status", status.value());
        return errorObject;
    }
}

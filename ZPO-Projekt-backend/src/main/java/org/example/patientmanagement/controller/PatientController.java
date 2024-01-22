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
import java.util.stream.Collectors;

/**
 * Klasa obsługująca żądania HTTP związane z zarządzeniem pacjentami
 *
 * @param <T> Typ pacjenta, generyczna klasa rozszerzająca model Pacjent
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://zpo-project.s3-website.eu-north-1.amazonaws.com/", methods = {RequestMethod.GET, RequestMethod.DELETE},
        allowedHeaders = {"Content-Type", "Authorization"},
        exposedHeaders = {"Content-Type", "Authorization"})
public class PatientController<T extends Patient> {

    private final PatientRepository patientRepository;

    /**
     * Konstruuje nową instancję `PatientController` z podanym `PatientRepository`
     *
     * @param patientRepository Repozytorium danych pacjenta
     */
    @Autowired
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Pobiera listę wszystkich pacjentów w formacie JSON
     *
     * @return ResponseEntity zawierające listę pacjentów i kod statusu OK
     */
    @GetMapping("/patients/all/json")
    public ResponseEntity<List<Patient>> getAllPatientsJson() {
        List<Patient> patients = patientRepository.findAll();
        return ResponseEntity.ok(patients);
    }

    /**
     * Pobiera listę dorosłych pacjentów (w wieku od 18 do 65 lat) w formacie JSON
     *
     * @return ResponseEntity zawierające listę dorosłych pacjentów i kod statusu OK
     */
    @GetMapping("/patients/adults/json")
    public ResponseEntity<List<Patient>> getAdultPatientsJson() {
        List<Patient> adultPatients = patientRepository.findAll()
                .stream()
                .filter(patient -> patient.getAge() >= 18 && patient.getAge() <= 65)
                .collect(Collectors.toList());

        return ResponseEntity.ok(adultPatients);
    }

    /**
     * Pobiera listę dziecięcych pacjentów (poniżej 18 roku życia) w formacie JSON
     *
     * @return ResponseEntity zawierające listę dziecięcych pacjentów i kod statusu OK
     */
    @GetMapping("/patients/children/json")
    public ResponseEntity<List<Patient>> getChildPatientsJson() {
        List<Patient> childPatients = patientRepository.findAll()
                .stream()
                .filter(patient -> patient.getAge() < 18)
                .collect(Collectors.toList());
        return ResponseEntity.ok(childPatients);
    }

    /**
     * Pobiera listę seniorów (powyżej 65 roku życia) w formacie JSON
     *
     * @return ResponseEntity zawierające listę seniorów i kod statusu OK
     */
    @GetMapping("/patients/seniors/json")
    public ResponseEntity<List<Patient>> getSeniorPatientsJson() {
        List<Patient> seniorPatients = patientRepository.findAll()
                .stream()
                .filter(patient -> patient.getAge() > 65)
                .collect(Collectors.toList());

        return ResponseEntity.ok(seniorPatients);
    }

    /**
     * Usuwa pacjenta o określonym ID
     *
     * @param id ID pacjenta do usunięcia
     * @return ResponseEntity z kodem NO_CONTENT w przypadku sukcesu lub odpowiedzią z błędem i
     *          odpowiednim kodem statusu w przypadku problemu.
     */
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

    /**
     * Tworzy mapę zawierającą informacje o błędzie
     *
     * @param message Wiadomość błędu
     * @param status  Kod statusu HTTP związany z błędem
     * @return Mapa zawierająca szczegóły błędu
     */
    private Map<String, Object> createErrorObject(String message, HttpStatus status) {
        Map<String, Object> errorObject = new HashMap<>();
        errorObject.put("error", message);
        errorObject.put("status", status.value());
        return errorObject;
    }
}

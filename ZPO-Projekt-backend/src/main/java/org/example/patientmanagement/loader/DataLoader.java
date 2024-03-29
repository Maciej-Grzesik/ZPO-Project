package org.example.patientmanagement.loader;

import org.example.patientmanagement.model.*;
import org.example.patientmanagement.repository.PatientRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Klasa `DataLoader` odpowiedzialna za ładowanie danych pacjentów z pliku JSON do bazy danych
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://zpo-project.s3-website.eu-north-1.amazonaws.com/",
        allowedHeaders = {"Content-Type", "Authorization"},
        exposedHeaders = {"Content-Type", "Authorization"})

public class DataLoader implements CommandLineRunner {
    private final PatientRepository patientRepository;

    /**
     * Konstruktor klasy `DataLoader` z repozytorium pacjentów
     *
     * @param patientRepository Repozytorium pacjentów
     */
    public DataLoader(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void run(String... args) { }

    /**
     * Obsługuje przesyłanie pliku JSON i dodaje pacjentów do bazy danych
     *
     * @param multipartFile Plik do przesłania
     * @return ResponseEntity z komunikatem o wyniku operacji
     * @throws IOException Występuje, gdy wystąpi błąd wejścia/wyjścia podczas przesyłania pliku
     */
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        File file = File.createTempFile("temp", null);
        multipartFile.transferTo(file);

        try (FileReader reader = new FileReader(file)) {
            JSONTokener jsonTokener = new JSONTokener(reader);
            JSONArray patientArray = new JSONArray(jsonTokener);

            savePatient(patientArray);

            return ResponseEntity.status(HttpStatus.OK).body("Data uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during uploading file");
        }
    }

    /**
     * Zapisuje pacjentów do bazy danych na podstawie przekazanej tablicy JSON
     *
     * @param patientArray Tablica JSON z danymi pacjentów
     */
    public void savePatient(JSONArray patientArray) {
        for (int i = 0; i < patientArray.length(); i++) {
            JSONObject patientObj = patientArray.getJSONObject(i);

            String type = patientObj.getString("type");
            Patient patient = null;

            switch (type) {
                case "senior":
                    patient = createSeniorPatient(patientObj);
                    break;
                case "adult":
                    patient = createAdultPatient(patientObj);
                    break;
                case "child":
                    patient = createChildPatient(patientObj);
                    break;
                default:
                    break;
            }

            if (patient != null) {
                savePatient(patient);
            }
        }
    }

    /**
     * Zapisuje pojedynczego pacjenta do bazy danych
     *
     * @param patient Pacjent do zapisania
     */
    private void savePatient(Patient patient) {
        try {
            System.out.println(patient.toString());
            patientRepository.save(patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tworzy obiekt pacjenta dorosłego na podstawie danych z obiektu JSON
     *
     * @param patientObj Obiekt JSON z danymi pacjenta dorosłego
     * @return Obiekt pacjenta dorosłego
     */
    private AdultPatientEntity createAdultPatient(JSONObject patientObj) {
        AdultPatientEntity adultPatientEntity = new AdultPatientEntity();
        fillCommonPatientFields(adultPatientEntity, patientObj);
        adultPatientEntity.setOccupation(patientObj.getString("occupation"));
        adultPatientEntity.setMartialStatus(MaritalStatus.valueOf(patientObj.getString("maritalStatus")));

        if (MaritalStatus.MARRIED.equals(adultPatientEntity.getMaritalStatus())) {
            adultPatientEntity.setSpouseName(patientObj.optString("spouseName", ""));
        }

        return adultPatientEntity;
    }

    /**
     * Tworzy obiekt pacjenta dziecka na podstawie danych z obiektu JSON
     *
     * @param patientObj Obiekt JSON z danymi pacjenta dziecka
     * @return Obiekt pacjenta dziecka
     */
    private ChildPatientEntity createChildPatient(JSONObject patientObj) {
        ChildPatientEntity childPatientEntity = new ChildPatientEntity();
        fillCommonPatientFields(childPatientEntity, patientObj);
        childPatientEntity.setSchoolGrade(patientObj.getString("schoolGrade"));
        childPatientEntity.setParentGuardian(patientObj.getString("parentGuardian"));

        return childPatientEntity;
    }

    /**
     * Tworzy obiekt pacjenta seniora na podstawie danych z obiektu JSON
     *
     * @param patientObj Obiekt JSON z danymi pacjenta seniora
     * @return Obiekt pacjenta seniora
     */
    private SeniorPatientEntity createSeniorPatient(JSONObject patientObj) {
        SeniorPatientEntity seniorPatientEntity = new SeniorPatientEntity();
        fillCommonPatientFields(seniorPatientEntity, patientObj);
        seniorPatientEntity.setMaritalStatus(MaritalStatus.valueOf(patientObj.getString("maritalStatus")));

        if (MaritalStatus.MARRIED.equals(seniorPatientEntity.getMaritalStatus())) {
            seniorPatientEntity.setSpouseName(patientObj.optString("spouseName", ""));
        }

        seniorPatientEntity.setRetirementPlan(patientObj.getString("retirementPlan"));

        return seniorPatientEntity;
    }

    /**
     * Uzupełnia wspólne pola danych pacjenta na podstawie obiektu JSON
     *
     * @param patient    Obiekt pacjenta do uzupełnienia
     * @param patientObj Obiekt JSON z danymi pacjenta
     */
    private void fillCommonPatientFields(Patient patient, JSONObject patientObj) {
        patient.setFirstName(patientObj.optString("firstName", ""));
        patient.setSecondName(patientObj.optString("secondName", ""));
        patient.setLastName(patientObj.optString("lastName", ""));
        patient.setAge(patientObj.optInt("age", 0));
        patient.setGender(Gender.valueOf(patientObj.getString("gender")));

        if (patientObj.has("insurances") && patientObj.get("insurances") instanceof JSONArray) {
            JSONArray insuranceArray = patientObj.getJSONArray("insurances");
            for (int i = 0; i < insuranceArray.length(); i++) {
                Insurance insurance = Insurance.valueOf(insuranceArray.getString(i));
                patient.addInsurance(insurance);
            }
        }
    }
}

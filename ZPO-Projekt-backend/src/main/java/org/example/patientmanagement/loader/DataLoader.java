package org.example.patientmanagement.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.patientmanagement.model.Patient;
import org.example.patientmanagement.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;

import java.io.InputStream;
import java.util.List;

public class DataLoader implements CommandLineRunner {
    private final String filePath;
    private final PatientRepository patientRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(PatientRepository patientRepository, ObjectMapper objectMapper, String filePath) {
        this.patientRepository = patientRepository;
        this.objectMapper = objectMapper;
        this.filePath = filePath;
    }

    @Override
    public void run(String... args) throws Exception {
        try(InputStream inputStream = TypeReference.class.getResourceAsStream(filePath)) {
            List<Patient> patientList = objectMapper.readValue(inputStream, new TypeReference<List<Patient>>() {});

            patientRepository.saveAll(patientList);
        }
    }
}

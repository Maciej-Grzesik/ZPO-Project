package org.example.patientmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Główna klasa aplikacji `PatientManagementApplication` służąca do uruchamiania aplikacji
 */
@SpringBootApplication
public class PatientManagementApplication {

    /**
     * Metoda main uruchamia aplikację Spring Boot
     *
     * @param args Argumenty wiersza poleceń
     */
    public static void main(String[] args) {
        SpringApplication.run(PatientManagementApplication.class, args);
    }

}

package org.example.patientmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Wyjątek `PatientNotFoundException` reprezentujący sytuację, gdy pacjent nie został znaleziony w systemie
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientNotFoundException extends RuntimeException{

    /**
     * Konstruktor wyjątku z określonym komunikatem
     *
     * @param errorMessage Komunikat błędu
     */
    public PatientNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

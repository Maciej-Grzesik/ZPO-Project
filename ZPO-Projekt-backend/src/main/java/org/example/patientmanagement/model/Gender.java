package org.example.patientmanagement.model;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other"),
    NOT_SPECIFIED("Not specified");

    final String displayGender;

    Gender(String displayGender) {
        this.displayGender = displayGender;
    }

    public String getDisplayGender() {
        return displayGender;
    }
}

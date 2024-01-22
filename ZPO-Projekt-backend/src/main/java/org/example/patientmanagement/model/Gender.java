package org.example.patientmanagement.model;

/**
 * Enum `Gender` reprezentujący płeć pacjenta
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other"),
    NOT_SPECIFIED("Not specified");

    private final String displayGender;

    /**
     * Konstruktor enuma z przypisanym wyświetlanym tekstem dla danej płci
     *
     * @param displayGender Tekst reprezentujący daną płci
     */
    Gender(String displayGender) {
        this.displayGender = displayGender;
    }

    /**
     * Zwraca tekst reprezentujący daną płci
     *
     * @return Tekst reprezentujący daną płci
     */
    public String getDisplayGender() {
        return displayGender;
    }
}

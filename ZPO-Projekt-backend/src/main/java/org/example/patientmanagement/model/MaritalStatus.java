package org.example.patientmanagement.model;

/**
 * Enum `MaritalStatus` reprezentujący stan cywilny pacjenta
 */
public enum MaritalStatus {
    SINGLE("Single"),
    MARRIED("Married"),
    DIVORCED("Divorced"),
    WIDOWED("Widowed");

    private final String displayName;

    /**
     * Konstruktor enuma z przypisanym wyświetlanym tekstem dla danego stanu cywilnego
     *
     * @param displayName Tekst reprezentujący dany stan cywilny
     */
    MaritalStatus(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Zwraca tekst reprezentujący dany stan cywilny
     *
     * @return Tekst reprezentujący dany stan cywilny
     */
    public String getDisplayName() {
        return displayName;
    }
}

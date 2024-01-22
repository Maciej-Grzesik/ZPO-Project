package org.example.patientmanagement.model;

/**
 * Enum `Insurance` reprezentujący rodzaje ubezpieczeń pacjenta
 */
public enum Insurance {
    RETIREMENT_DISABILITY("Retirement and disability"),
    VOLUNTARY("Voluntary"),
    MANDATORY("Mandatory"),
    SICKNESS("Sickness"),
    ACCIDENT("Accident");

    private final String displayName;

    /**
     * Konstruktor enuma z przypisanym wyświetlanym tekstem dla danego rodzaju ubezpieczenia
     *
     * @param displayName Tekst reprezentujący dany rodzaj ubezpieczenia
     */
    Insurance(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Zwraca tekst reprezentujący dany rodzaj ubezpieczenia.
     *
     * @return Tekst reprezentujący dany rodzaj ubezpieczenia
     */
    public String getDisplayName() {
        return displayName;
    }
}

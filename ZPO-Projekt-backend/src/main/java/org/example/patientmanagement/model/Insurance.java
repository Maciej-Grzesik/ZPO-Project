package org.example.patientmanagement.model;

public enum Insurance {
    RETIREMENT_DISABILITY("Retirement and disability"),
    VOLUNTARY("Voluntary"),
    MANDATORY("Mandatory"),
    SICKNESS("Sickness"),
    ACCIDENT("Accident");

    private final String displayName;

    Insurance(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}

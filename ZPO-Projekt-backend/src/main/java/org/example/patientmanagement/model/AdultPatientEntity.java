package org.example.patientmanagement.model;

import javax.persistence.*;

@Entity
@Table(name="adult_patients")
public class AdultPatientEntity extends Patient {
    private String occupation;
    private MaritalStatus maritalStatus;
    private String spouseName;

    public AdultPatientEntity() { }
    public String getOccupation() {
        return occupation;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder(super.toString());
        info.append("Occupation: ").append(occupation).append("\n");
        info.append("Martial status: ").append(maritalStatus).append("\n");
        if (maritalStatus == MaritalStatus.MARRIED && spouseName != null) {
            info.append("Spouse's name: ").append(spouseName).append("\n");
        }
        return info.toString();
    }
}

package org.example.patientmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "senior_patients")
public class SeniorPatientEntity extends Patient {
    private MaritalStatus maritalStatus;
    private String spouseName;
    private String retirementPlan;
    public SeniorPatientEntity() { }
    public String getRetirementPlan() {
        return retirementPlan;
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
        info.append("Retirement plan: ").append(retirementPlan).append("\n");
        info.append("Martial status: ").append(maritalStatus).append("\n");
        if (maritalStatus == MaritalStatus.MARRIED && spouseName != null) {
            info.append("Spouse's name: ").append(spouseName).append("\n");
        }
        return info.toString();
    }
}

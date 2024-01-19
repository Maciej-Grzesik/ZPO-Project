package org.example.patientmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "senior_patients")
@PrimaryKeyJoinColumn(name="patient_id")
public class SeniorPatientEntity extends Patient {

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status", nullable = false)
    private MaritalStatus maritalStatus;

    @Column(name = "spouse_name")
    private String spouseName;

    @Column(name = "retirement_plan")
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

    public void setRetirementPlan(String retirementPlan) {
        this.retirementPlan = retirementPlan;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder(super.toString());
        info.append("Retirement plan: ").append(retirementPlan).append("\n");
        info.append("Marital status: ").append(maritalStatus).append("\n");
        if (maritalStatus == MaritalStatus.MARRIED && spouseName != null) {
            info.append("Spouse's name: ").append(spouseName).append("\n");
        }
        return info.toString();
    }
}

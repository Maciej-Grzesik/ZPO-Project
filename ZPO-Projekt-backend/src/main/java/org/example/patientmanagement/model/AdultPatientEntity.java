package org.example.patientmanagement.model;

import javax.persistence.*;

@Entity
@Table(name="adult_patients")
@PrimaryKeyJoinColumn(name="patient_id")
public class AdultPatientEntity extends Patient {

    @Column(name = "occupation", nullable = false)
    private String occupation;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status", nullable = false)
    private MaritalStatus maritalStatus;

    @Column(name = "spouse_name")
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

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setMartialStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
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
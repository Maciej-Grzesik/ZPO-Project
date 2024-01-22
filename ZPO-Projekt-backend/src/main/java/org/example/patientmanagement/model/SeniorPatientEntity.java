package org.example.patientmanagement.model;

import javax.persistence.*;

/**
 * Klasa `SeniorPatientEntity` reprezentująca informacje o pacjencie seniorze
 */
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

    /**
     * Konstruktor domyślny klasy `SeniorPatientEntity`
     */
    public SeniorPatientEntity() { }

    /**
     * Zwraca informacje o planie emerytalnym pacjenta seniora
     *
     * @return Plan emerytalny pacjenta seniora
     */
    public String getRetirementPlan() {
        return retirementPlan;
    }

    /**
     * Zwraca stan cywilny pacjenta seniora
     *
     * @return Stan cywilny pacjenta seniora
     */
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Zwraca imię i nazwisko współmałżonka pacjenta seniora, jeśli pacjent jest żonaty
     *
     * @return Imię i nazwisko współmałżonka pacjenta seniora
     */
    public String getSpouseName() {
        return spouseName;
    }

    /**
     * Ustawia imię i nazwisko współmałżonka pacjenta seniora
     *
     * @param spouseName Imię i nazwisko współmałżonka pacjenta seniora
     */
    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    /**
     * Ustawia plan emerytalny pacjenta seniora
     *
     * @param retirementPlan Plan emerytalny pacjenta seniora
     */
    public void setRetirementPlan(String retirementPlan) {
        this.retirementPlan = retirementPlan;
    }

    /**
     * Ustawia stan cywilny pacjenta seniora
     *
     * @param maritalStatus Stan cywilny pacjenta seniora
     */
    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * Przedstawia informacje o pacjencie seniorze w formie tekstowej
     *
     * @return Tekstowa reprezentacja informacji o pacjencie seniorze
     */
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

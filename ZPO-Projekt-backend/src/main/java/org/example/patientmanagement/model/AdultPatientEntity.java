package org.example.patientmanagement.model;

import javax.persistence.*;

/**
 * Klasa `AdultPatientEntity` reprezentująca informacje o pacjencie dorosłym
 */
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

    /**
     * Konstruktor domyślny klasy `AdultPatientEntity`
     */
    public AdultPatientEntity() { }

    /**
     * Zwraca informacje o zawodzie pacjenta dorosłego
     *
     * @return Zawód pacjenta dorosłego
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Zwraca stan cywilny pacjenta dorosłego
     *
     * @return Stan cywilny pacjenta dorosłego
     */
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Zwraca imię i nazwisko współmałżonka pacjenta dorosłego, jeśli pacjent jest żonaty
     *
     * @return Imię i nazwisko współmałżonka pacjenta dorosłego
     */
    public String getSpouseName() {
        return spouseName;
    }

    /**
     * Ustawia imię i nazwisko współmałżonka pacjenta dorosłego
     *
     * @param spouseName Imię i nazwisko współmałżonka pacjenta dorosłego
     */
    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    /**
     * Ustawia zawód pacjenta dorosłego
     *
     * @param occupation Zawód pacjenta dorosłego
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Ustawia stan cywilny pacjenta dorosłego
     *
     * @param maritalStatus Stan cywilny pacjenta dorosłego
     */
    public void setMartialStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * Przedstawia informacje o pacjencie dorosłym w formie tekstowej
     *
     * @return Tekstowa reprezentacja informacji o pacjencie dorosłym
     */
    @Override
    public String toString() {
        StringBuilder info = new StringBuilder(super.toString());
        info.append("Occupation: ").append(occupation).append("\n");
        info.append("Marital status: ").append(maritalStatus).append("\n");
        if (maritalStatus == MaritalStatus.MARRIED && spouseName != null) {
            info.append("Spouse's name: ").append(spouseName).append("\n");
        }
        return info.toString();
    }
}

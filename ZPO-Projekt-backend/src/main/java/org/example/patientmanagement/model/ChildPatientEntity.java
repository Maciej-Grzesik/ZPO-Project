package org.example.patientmanagement.model;

import javax.persistence.*;

/**
 * Klasa `ChildPatientEntity` reprezentująca informacje o pacjencie dziecku
 */
@Entity
@Table(name = "child_patients")
@PrimaryKeyJoinColumn(name="patient_id")
public class ChildPatientEntity extends Patient {

    @Column(name = "school_grade")
    private String schoolGrade;

    @Column(name = "parent_guardian", nullable = false)
    private String parentGuardian;

    /**
     * Konstruktor domyślny klasy `ChildPatientEntity`
     */
    public ChildPatientEntity() { }

    /**
     * Zwraca informacje o klasie szkolnej pacjenta dziecka
     *
     * @return Klasa szkolna pacjenta dziecka
     */
    public String getSchoolGrade() {
        return schoolGrade;
    }

    /**
     * Zwraca informacje o rodzicu/prawnym opiekunie pacjenta dziecka
     *
     * @return Rodzic/prawny opiekun pacjenta dziecka
     */
    public String getParentGuardian() {
        return parentGuardian;
    }

    /**
     * Ustawia klasę szkolną pacjenta dziecka
     *
     * @param schoolGrade Klasa szkolna pacjenta dziecka
     */
    public void setSchoolGrade(String schoolGrade) {
        this.schoolGrade = schoolGrade;
    }

    /**
     * Ustawia rodzica/prawnego opiekuna pacjenta dziecka
     *
     * @param parentGuardian Rodzic/prawny opiekun pacjenta dziecka
     */
    public void setParentGuardian(String parentGuardian) {
        this.parentGuardian = parentGuardian;
    }

    /**
     * Przedstawia informacje o pacjencie dziecku w formie tekstowej
     *
     * @return Tekstowa reprezentacja informacji o pacjencie dziecku
     */
    @Override
    public String toString() {
        return super.toString() + "School Grade: " + schoolGrade + "\n" +
                "Parent/Legal guardian: " + parentGuardian + "\n";
    }
}

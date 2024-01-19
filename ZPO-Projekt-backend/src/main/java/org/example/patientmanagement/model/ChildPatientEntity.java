package org.example.patientmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "child_patients")
@PrimaryKeyJoinColumn(name="patient_id")
public class ChildPatientEntity extends Patient {

    @Column(name = "school_grade")
    private String schoolGrade;

    @Column(name = "parent_guardian", nullable = false)
    private String parentGuardian;

    public ChildPatientEntity() { }

    public String getSchoolGrade() {
        return schoolGrade;
    }

    public String getParentGuardian() {
        return parentGuardian;
    }

    public void setSchoolGrade(String schoolGrade) {
        this.schoolGrade = schoolGrade;
    }

    public void setParentGuardian(String parentGuardian) {
        this.parentGuardian = parentGuardian;
    }

    @Override
    public String toString() {
        return super.toString() + "School Grade: " + schoolGrade + "\n" +
                "Parent/Legal guardian: " + parentGuardian + "\n";
    }
}

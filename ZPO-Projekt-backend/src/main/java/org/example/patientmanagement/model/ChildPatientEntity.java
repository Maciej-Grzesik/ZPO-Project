package org.example.patientmanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "child_patients")
public class ChildPatientEntity extends Patient{
    private String shoolGrade;
    private String parentGuardian;

    public ChildPatientEntity() { }

    public String getShoolGrade() {
        return shoolGrade;
    }

    public String getParentGuardian() {
        return parentGuardian;
    }

    @Override
    public String toString() {
        return super.toString() + "School Grade: " + shoolGrade + "\n" +
                "Parent/Legal guardian: " + parentGuardian + "\n";
    }
}

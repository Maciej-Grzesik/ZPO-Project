package org.example.patientmanagement.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patients")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;
    private int age;
    private Gender gender;
    @ElementCollection
    private Set<Insurance> insurances = new HashSet<>();

    public Patient() { }

    public Long getId() { return id; }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public Set<Insurance> getInsurances() {
        return insurances;
    }

    public void addInsurance(Insurance insurance) {
        insurances.add(insurance);
    }

    public void removeInsurance(Insurance insurance) {
        insurances.remove(insurance);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Patient Information:\n");
        info.append("Name: ").append(firstName).append("\n");
        if (secondName != null) {
            info.append("Second Name: ").append(secondName).append("\n");
        }
        info.append("Last Name: ").append(lastName).append("\n"); // Zmiana: dodanie dwukropka
        info.append("Age: ").append(age).append("\n");
        info.append("Gender: ").append(gender).append("\n");
        info.append("Insurances:").append(insurances).append("\n");

        return info.toString();
    }
}

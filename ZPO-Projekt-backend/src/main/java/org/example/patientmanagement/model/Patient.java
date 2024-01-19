package org.example.patientmanagement.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "patients")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AdultPatientEntity.class, name = "adult"),
        @JsonSubTypes.Type(value = ChildPatientEntity.class, name = "child"),
        @JsonSubTypes.Type(value = SeniorPatientEntity.class, name = "senior")
})
public abstract class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @ElementCollection
    @CollectionTable(name = "insurance_mapping", joinColumns = @JoinColumn(name = "patient_id"))
    @Column(name = "insurance")
    @Enumerated(EnumType.STRING)
    private Set<Insurance> insurances = new HashSet<>();

    public Long getId() {
        return id;
    }

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
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder();
        info.append("Patient Information:\n");
        info.append("Name: ").append(firstName).append("\n");
        if (secondName != null) {
            info.append("Second Name: ").append(secondName).append("\n");
        }
        info.append("Last Name: ").append(lastName).append("\n");
        info.append("Age: ").append(age).append("\n");
        info.append("Gender: ").append(gender).append("\n");
        info.append("Insurances:").append(insurances).append("\n");

        return info.toString();
    }
}


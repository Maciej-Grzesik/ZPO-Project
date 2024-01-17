//package org.example.patientmanagement.repository;
//
//import org.example.patientmanagement.model.Patient;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class InMemoryPatientRepository implements PatientRepository {
//    private List<Patient> patients = new ArrayList<>();
//    private Long id = 1L;
//
//    @Override
//    public List<Patient> getAllPatients() {
//        return new ArrayList<>(patients);
//    }
//
//    @Override
//    public Optional<Patient> getPatientById(Long id) {
//        return patients.stream()
//                .filter(patient -> patient.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public List<Patient> findByLastName(String lastName) {
//        List<Patient> patientList = new ArrayList<>();
//        for (Patient patient : patientList) {
//            if (patient.getLastName().equals(lastName)) {
//                patientList.add(patient);
//            }
//        }
//        return patientList;
//    }
//
//    @Override
//    public Patient savePatient(Patient patient) {
//        if (patient.getId() == null) {
//            patient.setId(id++);
//        }
//        patients.add(patient);
//        return patient;
//    }
//
//    @Override
//    public void deletePatient(Long id) {
//        patients.removeIf(patient -> patient.getId().equals(id));
//    }
//}

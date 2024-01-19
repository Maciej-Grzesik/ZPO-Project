package org.example.patientmanagement;

import org.example.patientmanagement.controller.PatientController;
import org.example.patientmanagement.model.AdultPatientEntity;
import org.example.patientmanagement.model.ChildPatientEntity;
import org.example.patientmanagement.model.Patient;
import org.example.patientmanagement.model.SeniorPatientEntity;
import org.example.patientmanagement.repository.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PatientControllerTest {
    @Mock
    private PatientRepository patientRepository;
    @InjectMocks
    private PatientController patientController;

    @Test
    public void testGetAllPatientsJson(){
        List<Patient> mockAllPatients = Arrays.asList(new AdultPatientEntity(), new ChildPatientEntity());
        when(patientRepository.findAll()).thenReturn(mockAllPatients);

        ResponseEntity<List<Patient>> responseEntity = patientController.getAllPatientsJson();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, Objects.requireNonNull(responseEntity.getBody()).size());
    }

    @Test
    public void testGetAdultPatientsJson(){
        AdultPatientEntity adult1 = new AdultPatientEntity();
        adult1.setAge(40);
        AdultPatientEntity adult2 = new AdultPatientEntity();
        adult2.setAge(55);
        AdultPatientEntity adult3 = new AdultPatientEntity();
        adult3.setAge(45);
        List<Patient> mockAdultPatients = Arrays.asList(adult2, adult1, adult3);
        when(patientRepository.findAll()).thenReturn(mockAdultPatients);

        ResponseEntity<List<Patient>> responseEntity = patientController.getAdultPatientsJson();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(3, Objects.requireNonNull(responseEntity.getBody()).size());
    }

    @Test
    public void testGetChildPatientsJson(){
        ChildPatientEntity child1 = new ChildPatientEntity();
        ChildPatientEntity child2 = new ChildPatientEntity();
        ChildPatientEntity child3 = new ChildPatientEntity();
        child1.setAge(5);
        child2.setAge(10);
        child3.setAge(15);

        List<Patient> mockChildPatients = Arrays.asList(child1, child2, child3);
        when(patientRepository.findAll()).thenReturn(mockChildPatients);

        ResponseEntity<List<Patient>> responseEntity = patientController.getChildPatientsJson();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(3, Objects.requireNonNull(responseEntity.getBody()).size()
        );
    }

    @Test
    public void testGetSeniorPatientsJson(){
        SeniorPatientEntity senior1 = new SeniorPatientEntity();
        SeniorPatientEntity senior2 = new SeniorPatientEntity();
        SeniorPatientEntity senior3 = new SeniorPatientEntity();
        senior1.setAge(70);
        senior2.setAge(75);
        senior3.setAge(80);

        List<Patient> mockSeniorPatients = Arrays.asList(senior1, senior2, senior3);
        when(patientRepository.findAll()).thenReturn(mockSeniorPatients);

        ResponseEntity<List<Patient>> responseEntity = patientController.getSeniorPatientsJson();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(3, Objects.requireNonNull(responseEntity.getBody()).size());
    }

    @Test
    public void testDeletePatient() {
        long patientId = 1L;

        Patient mockPatient = new AdultPatientEntity();
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(mockPatient));

        ResponseEntity<Object> responseEntity = patientController.deletePatient(patientId);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(patientRepository, times(1)).delete(mockPatient);
    }

    @Test
    public void testDeletePatientNotFound() {
        long patientId = 1L;
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        ResponseEntity<Object> responseEntity = patientController.deletePatient(patientId);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testDeletePatientException() {
        long patientId = 1L;
        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());
        doThrow(new RuntimeException("Simulated error")).when(patientRepository).delete(any());

        ResponseEntity<Object> responseEntity = patientController.deletePatient(patientId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}

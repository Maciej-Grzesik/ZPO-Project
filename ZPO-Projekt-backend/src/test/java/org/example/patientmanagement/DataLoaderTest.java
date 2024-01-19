package org.example.patientmanagement;

import org.example.patientmanagement.loader.DataLoader;
import org.example.patientmanagement.model.Patient;
import org.example.patientmanagement.repository.PatientRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class DataLoaderTest {

    @Test
    void testHandleFileUpload_Success() throws IOException {
        String jsonContent = "[{\"type\":\"adult\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"age\":30,\"gender\":\"MALE\",\"occupation\":\"Engineer\",\"maritalStatus\":\"SINGLE\"}]";
        MockMultipartFile file = new MockMultipartFile("file", "test.json", "application/json", jsonContent.getBytes());

        PatientRepository patientRepository = mock(PatientRepository.class);
        DataLoader dataLoader = new DataLoader(patientRepository);

        ResponseEntity<String> responseEntity = dataLoader.handleFileUpload(file);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Data uploaded successfully!", responseEntity.getBody());
        verify(patientRepository, times(1)).save(any());
    }

    @Test
    void testHandleFileUpload_Error() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.json", "application/json", "invalid content".getBytes());

        PatientRepository patientRepository = mock(PatientRepository.class);

        DataLoader dataLoader = new DataLoader(patientRepository);

        ResponseEntity<String> responseEntity = dataLoader.handleFileUpload(file);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Error during uploading file", responseEntity.getBody());
        verify(patientRepository, never()).save(any());
    }

    @Test
    void testSavePatient() {
        PatientRepository patientRepository = mock(PatientRepository.class);

        String validPatientJson = "  {\n" +
                "    \"type\": \"adult\",\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Doe\",\n" +
                "    \"age\": 35,\n" +
                "    \"gender\": \"MALE\",\n" +
                "    \"insurances\": [\"MANDATORY\", \"VOLUNTARY\"],\n" +
                "    \"occupation\": \"Software Developer\",\n" +
                "    \"maritalStatus\": \"MARRIED\",\n" +
                "    \"spouseName\": \"Jane\"\n" +
                "  }";

        JSONArray patientArray = new JSONArray().put(new JSONObject(validPatientJson));
        DataLoader dataLoader = new DataLoader(patientRepository);

        dataLoader.savePatient(patientArray);

        verify(patientRepository, times(1)).save(any(Patient.class));
        assertNotNull(patientRepository.findByLastName("Doe"));
    }
}
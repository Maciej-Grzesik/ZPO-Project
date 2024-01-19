import axios from 'axios';

const BASE_URL = 'http://51.20.8.68:8080/api';

class PatientService {
    getAllPatients() {
        return axios.get(`${BASE_URL}/patients/all/json`);
    }

    getAdultPatients() {
        return axios.get(`${BASE_URL}/patients/adults/json`);
    }

    getChildPatients() {
        return axios.get(`${BASE_URL}/patients/children/json`);
    }

    getSeniorPatients() {
        return axios.get(`${BASE_URL}/patients/seniors/json`);
    }

    deletePatient(patientId) {
        return axios.delete(`${BASE_URL}/patients/json/${patientId}`);
    }
}

export default new PatientService();
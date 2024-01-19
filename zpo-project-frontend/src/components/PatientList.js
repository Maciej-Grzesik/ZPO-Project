import React, { useEffect, useState } from 'react';
import PatientService from './PatientService';
import './PatientList.css';

const PatientList = () => {
    const [patients, setPatients] = useState([]);
    const [childPatients, setChildPatients] = useState([]);
    const [adultPatients, setAdultPatients] = useState([]);
    const [seniorPatients, setSeniorPatients] = useState([]);
    const [patientType, setPatientType] = useState('All');
    const [activeType, setActiveType] = useState('All');
    const [dataLoaded, setDataLoaded] = useState(false);
    const [filteredPatients, setFilteredPatients] = useState([]);

    useEffect(() => {
        const loadData = async () => {
            try {
                const allPatients = await PatientService.getAllPatients();
                const childPatientsData = await PatientService.getChildPatients();
                const adultPatientsData = await PatientService.getAdultPatients();
                const seniorPatientsData = await PatientService.getSeniorPatients();

                setPatients(allPatients.data);
                setChildPatients(childPatientsData.data);
                setAdultPatients(adultPatientsData.data);
                setSeniorPatients(seniorPatientsData.data);

                setFilteredPatients(allPatients.data);
                setDataLoaded(true);
            } catch (error) {
                console.log(error);
            }
        };

        if (!dataLoaded) {
            loadData();
        }
    }, [dataLoaded]);

    useEffect(() => {
        // Update filtered patients whenever the patient data changes
        switch (patientType) {
            case 'All':
                setFilteredPatients(patients);
                break;
            case 'Child':
                setFilteredPatients(childPatients);
                break;
            case 'Adult':
                setFilteredPatients(adultPatients);
                break;
            case 'Senior':
                setFilteredPatients(seniorPatients);
                break;
            default:
                setFilteredPatients(patients);
        }
    }, [patients, childPatients, adultPatients, seniorPatients, patientType]);

    const handleTypeChange = (newType) => {
        setPatientType(newType);
        setActiveType(newType);
    };

    const deletePatient = async (patientId) => {
        try {
            await PatientService.deletePatient(patientId);
            await updateData();
        } catch (error) {
            console.log(error);
        }
    };

    const updateData = async () => {
        try {
            const updatedPatients = await PatientService.getAllPatients();
            setPatients(updatedPatients.data);

            const updatedChildPatients = await PatientService.getChildPatients();
            setChildPatients(updatedChildPatients.data);

            const updatedAdultPatients = await PatientService.getAdultPatients();
            setAdultPatients(updatedAdultPatients.data);

            const updatedSeniorPatients = await PatientService.getSeniorPatients();
            setSeniorPatients(updatedSeniorPatients.data);
        } catch (error) {
            console.log(error);
        }
    };
    

    const getPatientType = (patient) => {
        if (patient.age <= 18) {
            return 'Child';
        } else if (patient.age > 18 && patient.age <= 65) {
            return 'Adult';
        } else {
            return 'Senior';
        }
    };
    

    const renderTable = () => {
        if (patientType === 'Adult') {
            return (
                <table className="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Patient Id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Age</th>
                        <th>Gender</th>
                        <th>Insurances</th>
                        <th>Occupation</th>
                        <th>Marital Status</th>
                        <th>Spouse Name</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                        {
                            filteredPatients.map(
                                patient =>
                                    <tr key={patient.id}>
                                        <td>{patient.id}</td>
                                        <td>{patient.firstName}</td>
                                        <td>{patient.lastName}</td>
                                        <td>{patient.age}</td>
                                        <td>{patient.gender}</td>
                                        <td>{patient.insurances}</td>
                                        <td>{patient.occupation}</td>
                                        <td>{patient.maritalStatus}</td>
                                        <td>{patient.spouseName != null ? patient.spouseName : '---'}</td>
                                        <td>
                                            <button className='btn delete' onClick={() => deletePatient(patient.id)}> Delete </button>
                                        </td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            );
        } else if (patientType === 'Child') {
            return (
                <table className="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Patient Id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Age</th>
                        <th>Gender</th>
                        <th>Insurances</th>
                        <th>School Grade</th>
                        <th>Parent/Legal Guardian</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                        {
                            filteredPatients.map(
                                patient =>
                                    <tr key={patient.id}>
                                        <td>{patient.id}</td>
                                        <td>{patient.firstName}</td>
                                        <td>{patient.lastName}</td>
                                        <td>{patient.age}</td>
                                        <td>{patient.gender}</td>
                                        <td>{patient.insurances}</td>
                                        <td>{patient.schoolGrade}</td>
                                        <td>{patient.parentGuardian}</td>
                                        <td>
                                            <button className='btn delete' onClick={() => deletePatient(patient.id)}> Delete </button>
                                        </td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            );
        } else if (patientType === 'Senior') {
            return (
                <table className="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Patient Id</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Age</th>
                        <th>Gender</th>
                        <th>Insurances</th>
                        <th>Marital Status</th>
                        <th>Spouse Name</th>
                        <th>Retirement Plan</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                        {
                            filteredPatients.map(
                                patient =>
                                    <tr key={patient.id}>
                                        <td>{patient.id}</td>
                                        <td>{patient.firstName}</td>
                                        <td>{patient.lastName}</td>
                                        <td>{patient.age}</td>
                                        <td>{patient.gender}</td>
                                        <td>{patient.insurances}</td>
                                        <td>{patient.maritalStatus}</td>
                                        <td>{patient.spouseName != null ? patient.spouseName : '---'}</td>
                                        <td>{patient.retirementPlan}</td>
                                        <td>
                                            <button className='btn delete' onClick={() => deletePatient(patient.id)}> Delete </button>
                                        </td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            );
        } else {
            return (
                <table className="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Patient Id</th>
                        
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Age</th>
                        <th>Gender</th>
                        <th>Patient Type</th>
                        <th>Insurances</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                        {
                            filteredPatients.map(
                                patient =>
                                    <tr key={patient.id}>
                                        <td>{patient.id}</td>
                                        
                                        <td>{patient.firstName}</td>
                                        <td>{patient.lastName}</td>
                                        <td>{patient.age}</td>
                                        <td>{patient.gender}</td>
                                        <td>{getPatientType(patient)}</td>
                                        <td>{patient.insurances}</td>
                                        <td>
                                            <button className='btn delete' onClick={() => deletePatient(patient.id)}> Delete </button>
                                        </td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>
            );
        }
    };

    return (
        <div className="container">
            <h2 className="text-center"> List of Patients </h2>
            <div className='btn-group'>
                <button 
                    onClick={() => handleTypeChange('All')} 
                    className={activeType === 'All' ? 'active' : ''} // Dodane
                >
                    All
                </button>
                <button 
                    onClick={() => handleTypeChange('Child')} 
                    className={activeType === 'Child' ? 'active' : ''} // Dodane
                >
                    Child
                </button>
                <button 
                    onClick={() => handleTypeChange('Adult')} 
                    className={activeType === 'Adult' ? 'active' : ''} // Dodane
                >
                    Adult
                </button>
                <button 
                    onClick={() => handleTypeChange('Senior')} 
                    className={activeType === 'Senior' ? 'active' : ''} // Dodane
                >
                    Senior
                </button>
            </div>
            {renderTable()}
        </div>
    );
}

export default PatientList;
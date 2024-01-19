import React, { useState } from "react";
import './JsonUploader.css';

const JsonUploader = (props) => {
    const [selectedFile, setSelectedFile] = useState(null);
    const [patientsData, setPatientsData] = useState(null);

    const handleFileChange = (event) => {
        const file = event.target.files[0];
        setSelectedFile(file);
    };

    const handleUpload = () => {
        if (selectedFile) {
            const formData = new FormData();
            formData.append('file', selectedFile);

            fetch('http://51.20.8.68:8080/api/upload', {
                method: 'POST',
                body: formData,
                mode: 'cors',
            })
            .then((response) => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.text();
            })
            .then((data) => {
                console.log('Otrzymane dane:', data);
                props.onUploadSuccess();
            })
            .catch((error) => {
                console.error('Error: ', error);
            });
        }
    };
    
    return (
        <div className="json-container">
            <input type="file" onChange={handleFileChange} placeholder="Choose File" />
            <button onClick={handleUpload}>Send file</button>
        </div>
    );
};

export default JsonUploader;

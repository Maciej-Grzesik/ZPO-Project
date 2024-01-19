import { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import './App.css';
import JsonUploader from './components/JsonUploader';
import PatientList from './components/PatientList';
import Navbar from './components/Navbar';
import Footer from './components/Footer';

function App() {
  const [view, setView] = useState('uploader');

  const switchToPatientList = () => {
    setView('patientList');
  }

  return (
    <Router>
      
      <div className="App">
        <Navbar />
        <div className='body-content'>
          <Routes>
            <Route path="/" element={<JsonUploader onUploadSuccess={switchToPatientList} />} />
            <Route path="/table" element={<PatientList />} />
          </Routes>
        </div>
        <Footer />
      </div>
    </Router>
  );
}

export default App;

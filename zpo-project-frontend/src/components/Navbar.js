import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import React from 'react'
import './Navbar.css'

const Navbar = () => {
  return (
    <div className='navbar'>
        <ul>
          <li><Link to="/">Upload Json File</Link></li>
          <li><Link to="/table">Table</Link></li>
        </ul>
    </div>
  )
}

export default Navbar
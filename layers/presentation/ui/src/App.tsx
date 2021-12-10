import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import './App.css';
import Login from './login/Login';
import Landing from './landing/LandingPage';

const App = () => {
  return (
    <div>
    <BrowserRouter>
      <div>
          <Routes>
            <Route />
            <Route path="/" element={Landing()} />
            <Route path="/join" element={Login()} />
          </Routes>
      </div>
    </BrowserRouter>
    </div>
  );
}

export default App;

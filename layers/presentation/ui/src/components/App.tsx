import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import './App.css';
import Landing from './landing/LandingPage';
import SignUp from './login/SignUp';
import LogIn from './login/LogIn';

const App = () => {
  return (
    <div>
      <BrowserRouter>
        <div>
          <Routes>
            <Route />
            <Route path="/" element={Landing()} />
            <Route path="/login" element={<LogIn onSuccess={() => { }} authClient={{ login: (a, b, c, onError) => { onError() } }} />} />
            <Route path="/join" element={SignUp()} />
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;

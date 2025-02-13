import React, { Component } from 'react';
import { useState } from 'react';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import { BrowserRouter, Routes, Route } from "react-router";

//Make sure to run SpringBoot application beforehand
function App() {

    return (
       //There should be a homepage for login
       //Make a component that at least displays email, password, and button
       <BrowserRouter>
          <div>
            <Routes>
              <Route path="/" element={<Login />}></Route>
              <Route path="/dashboard" element={<Dashboard />}></Route>
            </Routes>
          </div>
       </BrowserRouter>
       
    );
  
}
export default App;
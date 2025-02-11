import React, { Component } from 'react';
import { useState } from 'react';
import Login from './components/Login';
import Dashboard from './components/Dashboard';
import { BrowserRouter, Routes, Route } from "react-router";

//Make sure to run SpringBoot application beforehand
function App() {

  //This will store the token in memory that authorizes the shopper
  const [token, setToken] = useState();

  //If token isn't set, then the user should go to log in page by default
  if(!token) {
    return <Login setToken={setToken} />
  }

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
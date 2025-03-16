import React from 'react';
import Login from './components/Login';
import Register from './components/Register';
import Dashboard from './components/Dashboard';
import Store from './components/Store';
import Account from './components/Account';
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
              <Route path="/register" element={<Register />}></Route>
              <Route path="/dashboard" element={<Dashboard />}></Route>
              <Route path="/store" element={<Store />}></Route>
              <Route path="/account" element={<Account />}></Route>
            </Routes>
          </div>
       </BrowserRouter>
       
    );
  
}
export default App;
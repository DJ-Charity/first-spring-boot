import React, { useState } from "react";
import axios from "axios";
import { useNavigate  } from 'react-router-dom';

//This is the log in for Shoppers or Administrators to log in
const Login = ( {setToken} ) => {
    
    //sets up what variables are being used
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    

    //whenever a field/name is changed, update its value
    const handleChange = (e) => {   
        const { name, value } = e.target;
        if (name === 'email') {
            setEmail(value);
        } else if (name === 'password') {
            setPassword(value);
        }
    }

    //handles login
    const login = () => {
        
        //get the info stored to put into put request
        axios(`http://localhost:8080/api/v1/auth/authenticate`, {
            method: "POST", 
            data: {
                email,
                password,
            }
            })
            .then((response) => {
                //get the token from the response
                console.log("It's good:", response);
                localStorage.setItem('token', response.data.token); 
                navigate('/dashboard');     //TODO: now need to somehow go to dashboard      
            })
            .catch(error => {
                //catch any errors so we can properly display to user
                //console.log(error)
                if (error.response) {
                    // The request was made and the server responded with a status code
                    // that falls out of the range of 2xx
                    console.log('Error data:', error.response.data);
                    console.log('Error status:', error.response.status);
                    console.log('Error headers:', error.response.headers);
                  } else if (error.request) {
                    // The request was made but no response was received
                    console.log('Error request:', error.request);
                  } else {
                    // Something happened in setting up the request that triggered an Error
                    console.log('Error message:', error.message);
                  }
                  console.log('Error config:', error.config);
                
            }); 

    }
    
        return( 
            <div>
                <h1>Login</h1>
                    <div><input type ="email" placeholder="Enter Email" value={email} name="email" onChange={handleChange}/></div>

                    <div><input type ="password" placeholder="Enter Password" value={password} name="password" onChange={handleChange}/></div>

                    <div><button type="submit" onClick={login}>Login</button></div>      
            </div>
        );
    
    //return will show the initial login page
    
}
export default Login;
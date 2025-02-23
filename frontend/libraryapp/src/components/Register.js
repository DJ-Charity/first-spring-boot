import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';
import "../styles/Register.css";

const Register = () => {
    //sets up what variables are being used
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [passwordCopy, setPasswordCopy] = useState('');
    const [firstname, setFirstname] = useState('');
    const [lastname, setLastname] = useState('');
    const [dob, setDob] = useState('');

    const [passwordError, setPasswordError] = useState(false);
    const [passwordRuleError, setPasswordRuleError] = useState(false);
    const [emailRequiredError, setEmailRequiredError] = useState(false);
    const navigate = useNavigate();
    

    //whenever a field/name is changed, update its value
    const handleChange = (e) => {   
        const { name, value } = e.target;
        if (name === 'email') {
            setEmail(value);
        } else if (name === 'password') {
            setPassword(value);
        } else if (name === 'passwordCopy') {
            setPasswordCopy(value); 
        } else if (name === 'firstname') {
            setFirstname(value);
        } else if (name === 'lastname') {
            setLastname(value);
        } else if (name === 'dob') {
            setDob(value);
        }
    }

    //handles register
    const register = () => {
        //Reset errors
        setEmailRequiredError(false);
        setPasswordError(false);
        setPasswordRuleError(false);

        //input validation
        if(!password.length || !passwordCopy.length || password!==passwordCopy){
            //if password is blank or the confirmation does not match
            setPasswordError(true);
            
        } else if(!(/[A-Z]/.test(password)) || !(/[a-z]/.test(password)) || !(/\d/.test(password)) || !(/[!@#$%^&*(),.?":{}|<>]/.test(password))) {
            //password does not meet requirements
            setPasswordRuleError(true);
        } else if(!(/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) || !email.length) {
            setEmailRequiredError(true);
        } else {
            //all fields meet requirements
            axios(`http://localhost:8080/api/v1/auth/register`, {
            method: "POST", 
            data: {
                firstname,
                lastname,
                email,
                password,
                dob
            },
            withCredentials: true,
            })
            .then((response) => {
                //get the token from the response
                console.log("It's good:", response);
                localStorage.setItem('token', response.data.token); 
                navigate('/dashboard');        
            })
            .catch(error => {
                //catch any errors so we can properly display to user
                console.log(error.response);
                
            });
        }

         

    }

    return( 
        <div>
            <div className="registerForm">    
                {/* Error messages */}
                {passwordError && <p>Password doesn't match or the field is empty</p>}
                {emailRequiredError && <p>Email is invalid or the field is empty</p>}
                {passwordRuleError && <p>Password must contain an undercase letter, and uppercase letter, a number, and a special character</p>}

                <h1>Register a new account</h1>
                    <div><input type ="text" placeholder="Enter First Name" value={firstname} name="firstname" onChange={handleChange}/></div>
                    <div><input type ="text" placeholder="Enter Last Name" value={lastname} name="lastname" onChange={handleChange}/></div>

                    <div><input type ="date" placeholder="Enter Your Date of Birth" value={dob} name="dob" onChange={handleChange}/></div>
                    
                    <div><input type ="email" placeholder="Enter Email" value={email} name="email" onChange={handleChange}/></div>

                    <div><input type ="password" placeholder="New Password" value={password} name="password" onChange={handleChange}/></div>
                    <div><input type ="password" placeholder="Confirm Password" value={passwordCopy} name="passwordCopy" onChange={handleChange}/></div>

                    <div><button type="submit" onClick={register}>Register</button></div> 

            </div>
             
        </div>
    );
};
export default Register;
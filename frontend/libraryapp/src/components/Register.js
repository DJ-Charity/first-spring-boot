import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from 'react-router-dom';
import moment from 'moment';
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
    const [passwordLengthError, setPasswordLengthError] = useState(false);
    const [emailRequiredError, setEmailRequiredError] = useState(false);
    const [emailExistsError, setEmailExistsError] = useState(false);

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
        setEmailExistsError(false);
        setPasswordError(false);
        setPasswordRuleError(false);
        setPasswordLengthError(false);
        //TODO: Need to add error for when email already exists in database

        //input validation
        if(!password.length || !passwordCopy.length || password!==passwordCopy){
            //if password is blank or the confirmation does not match
            setPasswordError(true);

        } else if(password.length < 8 || password.length > 16) {
            //if password is not between 8 and 16 characters
            setPasswordLengthError(true);
            
        } else if(!(/[A-Z]/.test(password)) || !(/[a-z]/.test(password)) || !(/\d/.test(password)) || !(/[!@#$%^&*(),.?":{}|<>]/.test(password))) {
            //password does not meet requirements
            setPasswordRuleError(true);

        } else if(!(/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) || !email.length) {
            //email must be in correct format
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
                //we need to catch an error for when email already exists
                console.log(error.response);
                if(error.response.data === "Email is already in use") {
                    setEmailExistsError(true);
                }          
            });
        }
    }
    
    const inputs = document.querySelectorAll('input');
    inputs.forEach(el => {
        el.addEventListener('blur', e => {
            if(e.target.value) {
            e.target.classList.add('dirty');
            } else {
            e.target.classList.remove('dirty');
            }
        })
    })

    return( 
        <div>
            <h1 className="logoTitle">Borealis Bookstore</h1>
            <div className="registerForm">         

                <h2 className="registerForm__title">Register Your New Account</h2>
                <table className="registerForm_table">
                    <tr><td>
                            <label className="Register_label">
                                <input className="Register_input" type ="text" value={firstname} name="firstname" onChange={handleChange}/>
                                <span className="registerPlaceholder">Enter First Name</span>
                            </label>
                    </td></tr>
                    <tr><td>
                            <label className="Register_label">
                                <input className="Register_input" type ="text" value={lastname} name="lastname" onChange={handleChange}/>
                                <span className="registerPlaceholder">Enter Last Name</span>
                            </label>
                    </td></tr>
                    <tr><td>
                            <label className="Register_label">
                                <span className="Register_dobspan">Enter Your Date of Birth</span>
                                <input className="Register_dobinput" type ="date" value={dob} name="dob" max={moment().format("YYYY-MM-DD")} onChange={handleChange}/>
                            </label>
                    </td></tr>
                    <tr><td>
                            <label className="Register_label">
                                <input className="Register_input" type ="email" value={email} name="email" onChange={handleChange}/>
                                <span className="registerPlaceholder">Enter Your Email</span>
                            </label>
                    </td></tr>
                    <tr><td>
                            <label className="Register_label">
                                <input className="Register_input" type ="password" value={password} name="password" onChange={handleChange}/>
                                <span className="registerPlaceholder">Enter New Password</span>
                            </label>
                    </td></tr>
                    <tr><td>
                            <label className="Register_label">
                                <input className="Register_input" type ="password" value={passwordCopy} name="passwordCopy" onChange={handleChange}/>
                                <span className="registerPlaceholder">Confirm New Password</span>
                            </label>
                    </td></tr>
                    <tr>
                        <td><button className="button" type="submit" onClick={register}>Register</button></td>
                    </tr>
                </table>
                    
                {/* Error messages */}
                {passwordError && <p className="error">Password doesn't match or the field is empty</p>}
                {emailRequiredError && <p className="error">Email is invalid or the field is empty</p>}
                {emailExistsError && <p className="error">Email is already in use</p>}
                {passwordRuleError && <p className="error">Password must contain an undercase letter, and uppercase letter, a number, and a special character</p>}
                {passwordLengthError && <p className="error">Password must be between 8 and 16 characters</p>}

            </div>
            <div className="footer">
                <h2>GitHub Project by <Link to="https://github.com/DJ-Charity/first-spring-boot">DJ-Charity</Link></h2>
            </div>
             
        </div>
    );
};
export default Register;
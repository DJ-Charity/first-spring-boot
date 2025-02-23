import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from 'react-router-dom';
import "../styles/Login.css";
/* TODO:
    -Log in is done, but I need to do Register too
    -Styling is tbd
    */
//This is the log in for Shoppers or Administrators to log in
const Login = () => {
    
    //sets up what variables are being used
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const [invalidLoginError, setInvalidLoginError] = useState(false);
    

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
                console.log(error);
                setInvalidLoginError(true);                          
            }); 
    }
    
    //return will show the initial login page
    return( 
        <div>
            <h1 className="logoTitle">Borealis Bookstore</h1>
            <div className="loginForm">
                <h2 className="loginForm__title">Log In</h2>
                    <div className="containers">         
                        <label className="fieldNames">
                            Email:&nbsp;
                            <input className="fields" type ="email" value={email} name="email" onChange={handleChange}/>
                        </label>   
                    </div>

                    <div className="containers">
                        <label className="fieldNames">
                            Password:&nbsp;
                            <input className="fields" type ="password" value={password} name="password" onChange={handleChange}/>
                        </label>
                    </div>

                    <div><button className="button" type="submit" onClick={login}>Login</button></div> 
                    {invalidLoginError && <p className="error">Invalid email or password. Please try again.</p>} 
                    
                    <p className="registerLink">If you do not have an account, <Link to="/register">Register now</Link></p> 
            </div>

            <div className="footer">
                <h2>GitHub Project by <Link to="https://github.com/DJ-Charity/first-spring-boot">DJ-Charity</Link></h2>
            </div>         
        </div>
    );
    
    
    
}
export default Login;
import { useState } from "react";

//This is the log in for Shoppers or Administrators to log in
const Login = () =>{
    //variables to store username and password
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    //function to handle login
    function login(event) {
        
        event.preventDefault();
    } 
    
    //return will show the initial login page
    return( 
        <div>
            <h1>Login</h1>
            <form onSubmit={login}>
                
                <div><input type ="username" placeholder="Enter Email" value={username} onChange={(e) => setUsername=(e.target.value)} required/></div>
                <div><input type ="password" placeholder="Enter Password" value={password} onChange={(e) => setPassword=(e.target.value)} required/></div>
                <div><button type="submit">Login</button></div>
            </form>
        </div>
    );
};

export default Login;
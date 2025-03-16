import React, { useEffect, useState } from 'react';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';
import axios from "axios";
import { useNavigate, Link } from 'react-router-dom';
import "../styles/Account.css";

const Account = () => {
    const [user, setUser] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        //Extract the token from the cookie
        const token = Cookies.get('token');
  
        console.log(token);
        if (token) {
          try {
            // Decode the token to get user information
            const decodedToken = jwtDecode(token);
            //sub is the email, so give it to user
            setUser(decodedToken.sub);
            
          } catch (error) {
            console.error('Invalid token:', error);
            
            
          }
        } else {
          navigate('/');
        }
  
      }, []);
      //END

    return(
        <div>
          <div className='header'>
              <div className='logo'>Borealis BookStore</div>
              <div><Link to="/dashboard">Dashboard</Link></div>
              <div><Link to="/store">Store</Link></div>
              <div>Log Out</div>
          </div>
  
          <h1>Account</h1>
  
        </div>
          
              
      );
};

export default Account;
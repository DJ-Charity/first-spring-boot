import React, { useEffect, useState } from 'react';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';
import axios from "axios";
import { useNavigate, Link } from 'react-router-dom';
import "../styles/Dashboard.css";


/* TODO:
    -Need a new database table for books(isbn key int, name string, author string, genre enum)
    -Dashboard is going to GET books from the table that the Shopper owns...meaning we need Shopper to store a list of books they have already bought
    -Eventually a seperate Store page will be made
*/
const Dashboard = () => {
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
            <div><Link to="/store">Store</Link></div>
            <div><Link to="/account">Account</Link></div>
            <div>Log Out</div>
        </div>

        <h1>{user}'s Books</h1>

      </div>
        
            
    );
};
export default Dashboard;
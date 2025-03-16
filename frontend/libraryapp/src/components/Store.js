import React, { useEffect, useState } from 'react';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';
import axios from "axios";
import { useNavigate, Link } from 'react-router-dom';
import "../styles/Store.css";

const Store = () => {
    const [user, setUser] = useState('');
    const [books, setBooks] = useState([]);
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
  
  
        //START: The code will be moved to Store page to show all books that can be purchased, the books are store is useState purchase
        axios(`http://localhost:8080/api/v1/shoppers/allbooks`, {
          method: "POST", 
          headers: {
            Authorization: `Bearer ${token}`,
          },
          data: {
              user,
          },
          withCredentials: true,
          })
          .then((response) => {
              //get the token from the response
              console.log("It's good:", response);
              setBooks(response.data);       
          })
          .catch(error => {
              //catch any errors so we can properly display to user
              console.log(error);                         
          }); 
  
      }, []);
      //END

    return(
        <div>
          <div className='header'>
              <div className='logo'>Borealis BookStore</div>
              <div><Link to="/dashboard">Dashboard</Link></div>
              <div><Link to="/account">Account</Link></div>
              <div>Log Out</div>
          </div>
  
          <h1>Store</h1>
  
          {/*TODO: This div will be moved to Store page to show all purchasable books*/}
          <div>
            <ul>
              {books.map((book) => (
                <li>{book.title}</li>
              ))}
            </ul>
          </div>
        </div>
          
              
      );
};

export default Store;
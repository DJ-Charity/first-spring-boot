import React, { useEffect, useState } from 'react';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';
/* TODO:
    -Need a new database table for books(isbn key int, name string, author string, genre enum)
    -Dashboard is going to GET books from the table that the Shopper owns...meaning we need Shopper to store a list of books they have already bought
    -Eventually a seperate Store page will be made
    -Styling of these pages is tbh, I want to focus on properly getting stuff from backend
*/
const Dashboard = () =>{
    const [user, setUser] = useState(null);

  useEffect(() => {
    //TODO: Finally got the cookie working, but I might need to cleanup my code in auth
    //try fixing the authentication response stuff
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
    }
  }, []);
    
    return(
        <h1>Dashboard {user}</h1>
       
    );
};
export default Dashboard;
import React, { useEffect, useState } from 'react';
import Cookies from 'js-cookie';
import { jwtDecode } from 'jwt-decode';
import axios from "axios";

/* TODO:
    -Need a new database table for books(isbn key int, name string, author string, genre enum)
    -Dashboard is going to GET books from the table that the Shopper owns...meaning we need Shopper to store a list of books they have already bought
    -Eventually a seperate Store page will be made
*/
const Dashboard = () => {
    const [user, setUser] = useState('');
    const [purchases, setPurchases] = useState([]);

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
            setPurchases(response.data);       
        })
        .catch(error => {
            //catch any errors so we can properly display to user
            console.log(error);                         
        }); 

    }, []);
    //END
      
    return(
      <div>
        <h1>Dashboard {user}</h1>

        {/*TODO: This div will be moved to Store page to show all purchasable books*/}
        <div>
          <ul>
            {purchases.map((purchase) => (
              <li>{purchase.title}</li>
            ))}
          </ul>
        </div>
      </div>
        
            
    );
};
export default Dashboard;
package com.springboot.first_spring_boot.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    /* JWT contains a 
    -header, has type of token and the algorithm used
    -payload, has claims(registered are set of predifined claims, public are claims that are defined in jwt registry or public by nature, or private that are custom made to share info through agreed parties) about the user and additional details
    -signature, used to verify the sender and make sure message was not changed along the way
    */

    private static final String SECRET_KEY = "6v7RsQTcK/+pGt7hsr+DgF3RJWFoSEm4t6LWHhnZiy0+s9HUNxhTDkjNFN/u56Qr";

    //Takes in token and returns username
    public String extractUsername(String jwt) {
       return extractClaim(jwt, Claims::getSubject);
    }

    //This will now extract any single claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        //when we decode token, we need signing key
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    //Token is generated with some extra claims, username, the time it was issued, and the time it will expire
    //it will then get signed with our key using the signature algorithm
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+1000*60)).signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
        //compact generates the token
    }

    //We can use this generate tokens instead if we have no extra claims
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    //validates token by checking if the user and the token have the same username and makes sure token is not expired
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
                
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
            
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
        
    private Key getSignInKey() {
        //we decode secret key using base 64
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        //hmac is an algorithm we give the decoded bytes to to return the key
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

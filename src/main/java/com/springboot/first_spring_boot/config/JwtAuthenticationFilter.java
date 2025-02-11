package com.springboot.first_spring_boot.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.first_spring_boot.shoppers.Shopper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request, //we can extract info from request to add to our response
        @NonNull HttpServletResponse response, 
        @NonNull FilterChain filterChain //contains chain of command of filters we need
        )    throws ServletException, IOException {
        
            //we need to extract the token that is in the request's header
            final String authHeader = request.getHeader("Authorization");

            //now we will check the jwt token
            final String jwt;
            final String username;
            if(authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return; //invalid token so return early
            }

            //token starts after "Bearer ", so starting index is 7
            jwt = authHeader.substring(7);

            //we use jwt service class to extract username
            username = jwtService.extractUsername(jwt);

            //TODO 1.13.50 for amigos jwt video
            //check is username is already authenticated
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //if authentication is null then user in not connected
                //now we need to see if user is in our database
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username); //TODO:user detailsservice is null for some reason

                if(jwtService.isTokenValid(jwt, userDetails)) {
                    //if token is valid, we need to update the security context and send request to dispatcher

                    //this token will help update the security context
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    
                    //This updates the context with our token
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            //always pass hand to the next filter to be executed
            filterChain.doFilter(request, response);
    }
    
}

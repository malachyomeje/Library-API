package com.finger.Godfinger.jwt;

import com.finger.Godfinger.service.StudentDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/authenticate")

public class JWTController {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private StudentDetailsService studentDetailsService;
    @PostMapping()

    public String getTokenForAuthenticatedUser(@RequestBody JWTAuthenticationRequest authRequest) {
        Authentication authentication;
            try {
                authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            } catch (AuthenticationException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        if (authentication.isAuthenticated()){
        UserDetails student= studentDetailsService.loadUserByUsername(authRequest.getUserName());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(student.toString());
          return jwtService.getGeneratedToken(authRequest.getUserName());
        }
        else {
            throw new UsernameNotFoundException("Invalid user credentials");
        }
    }
}



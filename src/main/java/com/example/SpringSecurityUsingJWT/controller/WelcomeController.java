package com.example.SpringSecurityUsingJWT.controller;

import com.example.SpringSecurityUsingJWT.entity.AuthRequest;
import com.example.SpringSecurityUsingJWT.entity.AuthResponse;
import com.example.SpringSecurityUsingJWT.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/welcome")
    public String welcome() {
        return "Access to welcome controller";
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> UserAuthenticate(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        }
        catch (Exception ex) {

            throw new Exception("Invalid username or password");

        }

        AuthResponse response = new AuthResponse(jwtUtil.generateToken(authRequest.getUserName()));

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }

}

package com.sales.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sales.dtos.AuthRequest;
import com.sales.dtos.AuthResponse;
import com.sales.entity.User;
import com.sales.service.SecurityService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    SecurityService securityService;


    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            AuthResponse response = securityService.login(request);
            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("register")
    public  ResponseEntity<?> register(@RequestBody @Valid AuthRequest request)
    {
        User registeredUser = securityService.register(request);
        return ResponseEntity.ok().body(String.format("User Success With Email [ %s ] Please Try To Login ",request.getEmail()));
    }


}
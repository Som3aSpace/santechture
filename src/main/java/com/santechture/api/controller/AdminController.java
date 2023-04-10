package com.santechture.api.controller;


import com.santechture.api.dto.GeneralResponse;
import com.santechture.api.dto.admin.AuthenticationRequest;
import com.santechture.api.dto.admin.AuthenticationResponse;
import com.santechture.api.exception.BusinessExceptions;
import com.santechture.api.service.AdminService;
import com.santechture.api.service.MyUserDetailsService;
import com.santechture.api.utils.JwtUtils;
import com.santechture.api.validation.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "admin")
public class AdminController {

    private final AdminService adminService;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtils jwtUtils;

    public AdminController(AdminService adminService, AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService, JwtUtils jwtUtils) {
        this.adminService = adminService;
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtUtils = jwtUtils;
    }

    // Deprecated
    @PostMapping
    public ResponseEntity<GeneralResponse> login(@RequestBody LoginRequest request) throws BusinessExceptions {
        return adminService.login(request);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<GeneralResponse> register(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return adminService.register(authenticationRequest);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
    public ResponseEntity<GeneralResponse> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

        } catch (BadCredentialsException e) {
            throw new BusinessExceptions("login.credentials.not.match");
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtils.generateToken(userDetails);
        return new GeneralResponse().response(new AuthenticationResponse(jwt, userDetails.getUsername()));
    }

}

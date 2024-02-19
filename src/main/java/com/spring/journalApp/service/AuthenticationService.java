package com.spring.journalApp.service;

import com.spring.journalApp.dto.JwtAuthenticationResponse;
import com.spring.journalApp.dto.SignInRequest;
import com.spring.journalApp.dto.SignUpRequest;
import com.spring.journalApp.entity.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
}

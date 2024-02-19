package com.spring.journalApp.service;

import com.spring.journalApp.dto.JwtAuthenticationResponse;
import com.spring.journalApp.dto.SignInRequest;
import com.spring.journalApp.dto.SignUpRequest;
import com.spring.journalApp.entity.Role;
import com.spring.journalApp.entity.User;
import com.spring.journalApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public User signup(SignUpRequest signUpRequest) {
        User user = new User();
        user.setUserName(signUpRequest.getUserName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);
        // can return JwtAuthenticationResponse (token) same as signIn method
        return user;
    }

    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword()));
        var user = userRepository.findByUserName(signInRequest.getUserName());
        if(user == null) {
            throw new IllegalArgumentException("Invalid name or password");
        }
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.refreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }
}

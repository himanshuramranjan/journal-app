package com.spring.journalApp.controller;

import com.spring.journalApp.entity.Role;
import com.spring.journalApp.entity.User;
import com.spring.journalApp.service.AuthenticationService;
import com.spring.journalApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        // once we authenticate the usdrDetails are available in the SecurityContextHolder
        // sp we can use it to access the authenticated urls
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User oldUser = userService.findByUserName(userName);
        if(oldUser != null) {
            oldUser.setUserName(user.getUsername());
            oldUser.setPassword(user.getPassword());
            oldUser.setRole(Role.USER);
            oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(oldUser);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

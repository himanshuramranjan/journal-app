package com.spring.journalApp.controller;

import com.spring.journalApp.entity.Role;
import com.spring.journalApp.entity.User;
import com.spring.journalApp.service.AuthenticationService;
import com.spring.journalApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        userService.saveUser(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@PathVariable String userName, @RequestBody User user) {
        User oldUser = userService.findByUserName(userName);
        if(oldUser != null) {
            oldUser.setUserName(user.getUsername());
            oldUser.setPassword(user.getPassword());
            userService.saveUser(oldUser);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

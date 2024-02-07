package com.spring.journalApp.controller;

import com.spring.journalApp.entity.User;
import com.spring.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@PathVariable String userName, @RequestBody User user) {
        User oldUser = userService.findByUserName(userName);
        if(oldUser != null) {
            oldUser.setUserName(user.getUserName());
            oldUser.setPassword(user.getPassword());
            userService.saveUser(oldUser);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

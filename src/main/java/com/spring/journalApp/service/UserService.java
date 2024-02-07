package com.spring.journalApp.service;

import com.spring.journalApp.entity.JournalEntry;
import com.spring.journalApp.entity.User;
import com.spring.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName) {
        return  userRepository.findByUserName(userName);
    }
}

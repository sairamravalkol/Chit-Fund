package com.jbtech.chit_fund.controller;

import com.jbtech.chit_fund.exception.ResourceNotFoundException;
import com.jbtech.chit_fund.model.User;
import com.jbtech.chit_fund.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if(user != null) {
            return user;
        }else {
            throw new ResourceNotFoundException("User not available");
        }

    }
    @GetMapping("/findByEmail/{email}")
    public User findByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        if(user != null) {
            return user;
        }else {
            throw new ResourceNotFoundException("User not available");
        }

    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
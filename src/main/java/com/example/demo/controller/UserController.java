package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.services.UserValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserValidateService userValidateService;

    @GetMapping("/")
    public String welcome(){
        return "Welcome You are Authenticated";
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> validateUser(@PathVariable String userId){
        User user= userValidateService.validate(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}

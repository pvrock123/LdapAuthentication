package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidateService  {
    @Autowired
    private UserRepository userRepository;

    public User validate(String userId) throws UserNotFoundException{
        User user=userRepository.findByUserId(userId);
        if(user==null){
            throw new UserNotFoundException("User Not Found");
        }
        return user;
    }
}

package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.exceptions.UserNotFoundException;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserValidateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class UserValidateServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserValidateService userValidateService;
    @Test
    public void validateTest_positive(){
        given(userRepository.findByUserId("prateek")).willReturn(new User("prateek","test"));
        User user=userValidateService.validate("prateek");
        assertThat(user.getUserId()).isEqualTo("prateek");
    }
    @Test(expected = UserNotFoundException.class)
    public void validateTest_negative(){
        given(userRepository.findByUserId("prateek")).willReturn(null);
        userValidateService.validate("prateek");
    }



}
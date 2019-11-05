package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;
import com.google.common.hash.Hashing;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    public void findByUidTest_positive(){
        User user= userRepository.findByUserId("prateek");
        Assertions.assertThat(user.getUserId()).isEqualTo("prateek");
        Assertions.assertThat(user.getFirstname()).isEqualTo("prateek");
        Assertions.assertThat(user.getLastname()).isEqualTo("vashist");
    }
    @Test
    public void findByUidTest_negative(){
        User user= userRepository.findByUserId("Invalid");
        Assertions.assertThat(user).isNull();
    }

}

package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.domain.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.services.UserValidateService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserValidateService userValidateService;
    @Test
    public void validateUserTest_positive() throws Exception{
        given(userValidateService.validate(anyString())).willReturn(new User("prateek","test"));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/prateek").with(user("prateek").password("test")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("userId").value("prateek"))
                .andExpect(jsonPath("pwd").value("test"));
    }

    @Test
    public void validateUserTest_negative() throws Exception{
        given(userValidateService.validate(anyString())).willThrow(new UserNotFoundException("User Not Found"));
        mockMvc.perform(MockMvcRequestBuilders.get("/users/prateek").with(user("prateek").password("test")))
                .andExpect(status().isNotFound());
    }

}

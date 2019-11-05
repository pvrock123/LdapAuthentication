package com.example.demo;
import com.example.demo.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IntegrationTest {
    @Value("${local.server.port}")
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testApp_positiveCredentials() throws Exception{
        ResponseEntity<User> response=restTemplate.withBasicAuth("prateek","test").getForEntity(
                "http://localhost:"+port+"/users/prateek",User.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getUserId()).isEqualTo("prateek");
        Assertions.assertThat(response.getBody().getFirstname()).isEqualTo("prateek");
        Assertions.assertThat(response.getBody().getLastname()).isEqualTo("vashist");


    }
    @Test
    public void testApp_InvalidCredentials() throws Exception {
        ResponseEntity<User> response=restTemplate.withBasicAuth("invalid","invalid").getForEntity(
                "http://localhost:"+port+"/users/prateek",User.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

}

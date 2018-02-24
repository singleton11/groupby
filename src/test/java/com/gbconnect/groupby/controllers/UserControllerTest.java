package com.gbconnect.groupby.controllers;

import com.gbconnect.groupby.GroupByApplication;
import com.gbconnect.groupby.domain.Login;
import com.gbconnect.groupby.domain.Token;
import com.gbconnect.groupby.domain.User;
import com.gbconnect.groupby.helpers.RegisterRequest;
import com.gbconnect.groupby.persistence.UserRepository;
import com.gbconnect.groupby.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = GroupByApplication.class
)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Before
    public void setUp() {
        userRepository.deleteAll();
        // Create user with email "test1@test.test"
        userRepository.save(User.builder().username("test1@test.test").password("test").build());
    }

    @Test
    public void registerReturns201StatusCode() {
        ResponseEntity<User> responseEntity = restTemplate
                .postForEntity(
                        "/users/register",
                        RegisterRequest.builder().email("test@test.test").password("test").build(),
                        User.class
                );

        User user = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("test@test.test", user.getUsername());
    }

    @Test
    public void registerUserWithTheSameEmailReturns400StatusCode() {
        ResponseEntity<User> responseEntity = restTemplate
                .postForEntity(
                        "/users/register",
                        RegisterRequest.builder().email("test1@test.test").password("test").build(),
                        User.class
                );
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void obtainJWTTokenWithCorrectCredentials() {
        ResponseEntity<Token> responseEntity = restTemplate
                .postForEntity(
                        "/users/getToken",
                        RegisterRequest.builder().email("test1@test.test").password("test").build(),
                        Token.class
                );
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void obtainJWTTokenWithWrongCredentials() {
        ResponseEntity<Token> responseEntity = restTemplate
                .postForEntity(
                        "/users/getToken",
                        RegisterRequest.builder().email("test@test.test").password("test").build(),
                        Token.class
                );
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void getMyProfileWithCorrectJWT() {
        Login login = new Login();
        login.setEmail("test1@test.test");
        Token token = jwtService.generateToken(login);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token.getKey());
        HttpEntity<User> httpEntity = restTemplate
                .exchange("/users/profile", HttpMethod.GET, new HttpEntity<>(headers), User.class);
        User user = httpEntity.getBody();
        assertEquals("test1@test.test", user.getUsername());
    }

    @Test
    public void getMyProfileWithWrongJWT() {
        Claims claims = Jwts.claims().setSubject("test2@test.test");
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, "[B@1d9af731").compact();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<User> httpEntity = restTemplate
                .exchange("/users/profile", HttpMethod.GET, new HttpEntity<>(headers), User.class);
        assertEquals(HttpStatus.FORBIDDEN, ((ResponseEntity)httpEntity).getStatusCode());
    }

    @Test
    public void getMyProfileWithSomethingInsteadJWT() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + "something wrong");
        HttpEntity<User> httpEntity = restTemplate
                .exchange("/users/profile", HttpMethod.GET, new HttpEntity<>(headers), User.class);
        assertEquals(HttpStatus.FORBIDDEN, ((ResponseEntity)httpEntity).getStatusCode());
    }
}
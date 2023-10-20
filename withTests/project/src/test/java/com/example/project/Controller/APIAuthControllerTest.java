package com.example.project.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;

import com.example.project.business.LoginBody;
import com.example.project.business.TokenClass;
import com.example.project.controller.APIAuthController;
import com.example.project.repository.UserRepository;

@SpringBootTest

class APIAuthControllerTest {

    @Mock
    private JwtEncoder jwtEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private APIAuthController authController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToken() {
        // Your token test logic here

        // Example: mock authentication
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername("testuser");
        loginBody.setPassword("testpassword");

        Authentication authentication = new UsernamePasswordAuthenticationToken("testuser", "testpassword",
                new ArrayList<>());

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", "testuser"); // Sample subject claim
        claims.put("exp", Instant.now().plusSeconds(3600).getEpochSecond()); // Sample expiration claim

        Jwt jwt = new Jwt("tokenValue", Instant.now(), Instant.now().plusSeconds(3600),
                Collections.singletonMap("alg", "HS256"), claims);

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtEncoder.encode(any())).thenReturn(jwt);

        TokenClass token = authController.token(loginBody);
        assertEquals("tokenValue", token.getToken());

        // Add more assertions based on your token generation logic
    }

    @Test
    void testRegisterUser() {
        // Your register user test logic here

        // Example: mock UserRepository
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername("newuser");
        loginBody.setPassword("newpassword");

        when(userRepository.existsByName("newuser")).thenReturn(false);

        ResponseEntity<String> responseEntity = authController.registerUser(loginBody);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }
}

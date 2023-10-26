
package com.example.project.controller;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.business.LoginBody;
import com.example.project.business.TokenClass;
import com.example.project.entity.User;
import com.example.project.exception.UserRegistrationException;
import com.example.project.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class APIAuthController {

    @Autowired
    JwtEncoder jwtEncoder;

    @Autowired
    UserRepository userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/token")
    @ResponseBody
    public TokenClass token(@RequestBody LoginBody loginBody) {
        Instant now = Instant.now();
        long expiry = 3600L;
        var username = loginBody.getUsername();
        var password = loginBody.getPassword();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        TokenClass token = new TokenClass();
        token.setToken(this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
        return token;
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> registerUser(@RequestBody LoginBody loginBody) {

        if (userRepo.existsByName(loginBody.getUsername())) {
            throw new UserRegistrationException("Username already exists");
        }

        User user = new User();
        user.setName(loginBody.getUsername());
        user.setPassword(passwordEncoder.encode(loginBody.getPassword()));
        userRepo.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

}

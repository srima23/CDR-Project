// package com.example.project;

// import java.security.interfaces.RSAPublicKey;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.oauth2.jwt.JwtEncoder;
// import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

// import com.nimbusds.jose.jwk.JWK;
// import com.nimbusds.jose.jwk.JWKSet;
// import com.nimbusds.jose.jwk.RSAKey;
// import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
// import com.nimbusds.jose.jwk.source.JWKSource;

// @Configuration
// @EnableWebSecurity
// @SpringBootTest
// public class BasicConfigurationTest {

// private final RSAPublicKey pub;

// @Autowired
// public BasicConfigurationTest(@Value("${jwt.public.key}") RSAPublicKey pub) {
// this.pub = pub;
// }

// // Rest of your configuration methods...

// @Bean
// JwtEncoder jwtEncoder() {
// final JWK jwk = new RSAKey.Builder(this.pub).build(); // Ensure this.pub is
// not null
// JWKSource<com.nimbusds.jose.proc.SecurityContext> jwks = new
// ImmutableJWKSet<>(new JWKSet(jwk));
// return new NimbusJwtEncoder(jwks);
// }

// // Other beans and configuration methods...
// }

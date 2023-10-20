// package com.example.project;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
// import static org.mockito.Mockito.*;

// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.security.core.userdetails.UserDetails;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.password.PasswordEncoder;

// import com.example.project.entity.User;
// import com.example.project.service.CustomDetailService;
// import com.example.project.service.UserService;

// @SpringBootTest

// class CustomDetailServiceTest {

// private CustomDetailService customDetailService;
// private UserService userService;
// private PasswordEncoder passwordEncoder;

// @BeforeEach
// void setUp() {
// userService = mock(UserService.class);
// passwordEncoder = mock(PasswordEncoder.class);
// }

// @Test
// void loadUserByUsername_userFound() {
// String username = "testUser";
// String encodedPassword = "encodedPassword";

// User user = new User();
// user.setName(username);
// user.setPassword(encodedPassword);

// when(userService.getByName(username)).thenReturn(Optional.of(user));
// when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);

// UserDetails userDetails = customDetailService.loadUserByUsername(username);

// assertNotNull(userDetails);
// assertEquals(username, userDetails.getUsername());
// assertEquals(encodedPassword, userDetails.getPassword());

// verify(userService, times(1)).getByName(username);
// verify(passwordEncoder, times(1)).encode(user.getPassword());
// }

// @Test
// void loadUserByUsername_userNotFound() {
// String username = "nonExistentUser";

// when(userService.getByName(username)).thenReturn(Optional.empty());

// assertThrows(UsernameNotFoundException.class, () ->
// customDetailService.loadUserByUsername(username));

// verify(userService, times(1)).getByName(username);
// verify(passwordEncoder, never()).encode(anyString());
// }
// }

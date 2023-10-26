package com.example.project.Service;
// package com.example.project;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// import com.example.project.entity.User;
// import com.example.project.repository.UserRepository;
// import com.example.project.service.UserService;

// @ExtendWith(MockitoExtension.class)
// public class UserServiceTest {

// @Mock
// private UserRepository userRepository;

// @Mock
// private BCryptPasswordEncoder passwordEncoder;

// @Test
// public void testCreateUser() {
// // Create a user
// User user = new User();
// user.setId(1L);
// user.setName("John Doe");
// user.setPassword("password"); // Assuming plain text password

// // Mock the password encoder
// when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

// // Mock the repository's save method
// when(userRepository.save(user)).thenReturn(user);

// // Instantiate the UserService
// UserService userService = new UserService(userRepository, passwordEncoder);

// // Call the create method
// User createdUser = userService.create(user);

// // Verify that the password was encoded and the user was saved
// assertEquals("encodedPassword", createdUser.getPassword());
// verify(userRepository, times(1)).save(user);
// }

// @Test
// public void testGetById() {
// // Create a user to be returned by the mock repository
// User user = new User();
// user.setId(1L);
// user.setName("John Doe");

// // Configure the mock repository to return the user when findById is called
// when(userRepository.findById((int) 1L)).thenReturn(Optional.of(user));

// // Instantiate the UserService
// UserService userService = new UserService(userRepository, passwordEncoder);

// // Call the getById method and verify the result
// Optional<User> result = userService.getById(1L);

// // Verify that the result matches the user created earlier
// assertTrue(result.isPresent());
// assertEquals(user, result.get());
// }
// }

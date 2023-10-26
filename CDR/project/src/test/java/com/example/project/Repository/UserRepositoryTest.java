package com.example.project.Repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.entity.User;
import com.example.project.repository.UserRepository;

@SpringBootTest

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void testFindByName() {
        // Create a user to be returned by the mock repository
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        // Configure the mock repository to return the user when findByName is called
        when(userRepository.findByName("John Doe")).thenReturn(Optional.of(user));

        // Call the findByName method and verify the result
        Optional<User> result = userRepository.findByName("John Doe");

        // Verify that the result matches the user created earlier
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void testExistsByName() {
        // Configure the mock repository to return true when existsByName is called with "John Doe"
        when(userRepository.existsByName("John Doe")).thenReturn(true);

        // Call the existsByName method and verify the result
        boolean result = userRepository.existsByName("John Doe");

        // Verify that the result is true
        assertTrue(result);
    }

    @Test
    void testFindRoleByName() {
        // Configure the mock repository to return a role for a given username
        when(userRepository.findRoleByName("john_doe")).thenReturn("ROLE_USER");

        // Call the findRoleByName method and verify the result
        String result = userRepository.findRoleByName("john_doe");

        // Verify that the correct role is returned
        assertEquals("ROLE_USER", result);
    }
}

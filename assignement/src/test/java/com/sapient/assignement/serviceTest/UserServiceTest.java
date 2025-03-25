package com.sapient.assignement.serviceTest;

import com.sapient.assignement.api.model.User;
import com.sapient.assignement.api.repository.UserRepository;
import com.sapient.assignement.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserServiceTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserByUsername_Success() {

        String username = "testUser";
        User mockUser = new User();
        mockUser.setUsername(username);
        when(userRepository.findByUsername(username)).thenReturn(mockUser);
        User result = userService.getUserByUsername(username);
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    public void testGetUserByUsername_NotFound() {
        String username = "nonExistentUser";
        when(userRepository.findByUsername(username)).thenReturn(null);
        User result = userService.getUserByUsername(username);
        assertNull(result);
    }

    @Test
    public void testGetAllUsers_Success() {

        List<User> mockUsers = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        mockUsers.add(user1);
        mockUsers.add(user2);

        when(userRepository.findAll()).thenReturn(mockUsers);
        List<User> result = userService.getAllUsers();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).getUsername());
        assertEquals("user2", result.get(1).getUsername());
    }

    @Test
    public void testGetAllUsers_EmptyList() {

        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getAllUsers();
        });
        assertEquals("An error occurred while retrieving all users", exception.getMessage());
    }

    @Test
    public void testGetAllUsers_Exception() {

        when(userRepository.findAll()).thenThrow(new RuntimeException("Database error"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getAllUsers();
        });
        assertEquals("An error occurred while retrieving all users", exception.getMessage());
        assertTrue(exception.getCause() instanceof RuntimeException);
        assertEquals("Database error", exception.getCause().getMessage());
    }
}

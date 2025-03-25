package com.sapient.assignement.controllerTest;

import com.sapient.assignement.api.controller.UserController;
import com.sapient.assignement.api.model.User;
import com.sapient.assignement.api.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("John Doe");
        user.setAge(30);
    }

    @Test
    void testLoadUsers_Success() {

        doNothing().when(userService).loadUsers();
        ResponseEntity<String> response = userController.loadUsers();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Users loaded successfully!", response.getBody());
    }

    @Test
    void testLoadUsers_Failure() {
        doThrow(new RuntimeException("Load users failed")).when(userService).loadUsers();
        ResponseEntity<String> response = userController.loadUsers();
        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Error loading users"));
    }

    @Test
    void testGetAllUsers() {

        when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));
        var response = userController.getAllUsers();
        assertEquals(1, response.size());
        assertEquals("John Doe", response.get(0).getUsername());
    }

    @Test
    void testGetUsersByRole() {

        String role = "admin";
        when(userService.getUsersByRole(role)).thenReturn(Collections.singletonList(user));
        var response = userController.getUsersByRole(role);
        assertEquals(1, response.size());
        assertEquals("John Doe", response.get(0).getUsername());
    }

    @Test
    void testGetUsersSortedByAgeAsc() {

        when(userService.getUsersSortedByAgeAsc()).thenReturn(Collections.singletonList(user));
        var response = userController.getUsersSortedByAgeAsc();
        assertEquals(1, response.size());
        assertEquals(30, response.get(0).getAge());
    }

    @Test
    void testGetUsersSortedByAgeDesc() {

        when(userService.getUsersSortedByAgeDesc()).thenReturn(Collections.singletonList(user));
        var response = userController.getUsersSortedByAgeDesc();
        assertEquals(1, response.size());
        assertEquals(30, response.get(0).getAge());
    }

    @Test
    void testGetUserByIdOrSsn() {

        Long id = 1L;
        String ssn = "123-45-6789";
        when(userService.getUserByIdOrSsn(id, ssn)).thenReturn(user);
        User response = userController.getUserByIdOrSsn(id, ssn);
        assertNotNull(response);
        assertEquals("John Doe", response.getUsername());
    }
}


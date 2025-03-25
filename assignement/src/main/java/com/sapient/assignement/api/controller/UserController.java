package com.sapient.assignement.api.controller;


import com.sapient.assignement.api.model.User;
import com.sapient.assignement.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/load")
    public ResponseEntity<String> loadUsers() {
        try {
            userService.loadUsers();
            return ResponseEntity.ok("Users loaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error loading users: " + e.getMessage());
        }
    }


        @GetMapping("/getAllUsers")
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        public List<User> getAllUsers() {
            return userService.getAllUsers();
        }


        @GetMapping("/role/{role}")
        public List<User> getUsersByRole(@PathVariable String role) {
            return userService.getUsersByRole(role);
        }

        @GetMapping("/age/asc")
        public List<User> getUsersSortedByAgeAsc() {
            return userService.getUsersSortedByAgeAsc();
        }

        @GetMapping("/age/desc")
        public List<User> getUsersSortedByAgeDesc() {
            return userService.getUsersSortedByAgeDesc();
        }

        @GetMapping("/find")
        public User getUserByIdOrSsn(@RequestParam(required = false) Long id, @RequestParam(required = false) String ssn) {
            return userService.getUserByIdOrSsn(id, ssn);
        }
    }
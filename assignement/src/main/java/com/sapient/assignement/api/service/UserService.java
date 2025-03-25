package com.sapient.assignement.api.service;


import com.sapient.assignement.api.model.User;
import com.sapient.assignement.api.model.UsersResponse;
import com.sapient.assignement.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_API_URL = "https://dummyjson.com/users";



    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public void loadUsers() {
        try {

            ResponseEntity<UsersResponse> responseEntity = restTemplate.exchange(
                    USER_API_URL,
                    HttpMethod.GET,
                    null,
                    UsersResponse.class
            );

            UsersResponse userListResponse = responseEntity.getBody();


            if (userListResponse == null || userListResponse.getUsers() == null || userListResponse.getUsers().isEmpty()) {
                throw new RuntimeException("No users found in the API response");
            }


            for (User user : userListResponse.getUsers()) {
                userRepository.save(user);
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {

            throw new RuntimeException("Error occurred while fetching users from external API: " + e.getStatusCode() + " - " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {

            throw new RuntimeException("An unexpected error occurred while loading users", e);
        }
    }

    // Get all users from the database
    public List<User> getAllUsers() {
        try {
            List<User> users = userRepository.findAll(); // Fetch all users

            if (users == null || users.isEmpty()) {
                throw new RuntimeException("No users found in the database");
            }

            return users;

        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving all users", e);
        }
    }


    public List<User> getUsersByRole(String role) {
        try {
            List<User> users = userRepository.findByRole(role);

            if (users == null || users.isEmpty()) {
                throw new RuntimeException("No users found for the given role: " + role);
            }

            return users;

        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving users by role", e);
        }
    }

    // Get users sorted by age in ascending order
    public List<User> getUsersSortedByAgeAsc() {
        try {
            return userRepository.findAllByOrderByAgeAsc();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving users sorted by age in ascending order", e);
        }
    }

    // Get users sorted by age in descending order
    public List<User> getUsersSortedByAgeDesc() {
        try {
            return userRepository.findAllByOrderByAgeDesc();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving users sorted by age in descending order", e);
        }
    }

    // Get user by ID or SSN
    public User getUserByIdOrSsn(Long id, String ssn) {
        try {
            User user = userRepository.findByIdOrSsn(id, ssn);

            if (user == null) {
                throw new RuntimeException("User not found with ID: " + id + " or SSN: " + ssn);
            }

            return user;

        } catch (Exception e) {
            throw new RuntimeException("An error occurred while retrieving user by ID or SSN", e);
        }
    }
}

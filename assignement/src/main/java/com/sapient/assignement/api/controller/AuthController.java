package com.sapient.assignement.api.controller;

import com.sapient.assignement.api.JWT.JwtResponse;
import com.sapient.assignement.api.JWT.JwtUtil;
import com.sapient.assignement.api.model.JwtRequest;
import com.sapient.assignement.api.model.User;
import com.sapient.assignement.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) {
        User user = userService.getUserByUsername(jwtRequest.getUsername());

        if (user != null && jwtRequest.getPassword().equals(user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
            return ResponseEntity.ok().body(new JwtResponse(user,token)); // Return token with "Bearer " prefix
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }

}
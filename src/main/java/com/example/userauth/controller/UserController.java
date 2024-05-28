package com.example.userauth.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userauth.entity.User;
import com.example.userauth.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody User user) {
        // Authentication is handled by Spring Security, so this endpoint can be left empty
        return ResponseEntity.ok("User signed in successfully");
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(Authentication authentication) {
    	String username = authentication.getName();
        User user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("User logged in successfully:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Firstname: " + user.getFirstname());
        System.out.println("Lastname: " + user.getLastname());
//    	String obj = Arrays.toString();
        String userData = user.getUsername().toString() + user.getFirstname().toString() + user.getLastname().toString();
    	return ResponseEntity.ok("User profile accessed" + userData);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> signout() {
        // Signout handled by Spring Security
        return ResponseEntity.ok("User signed out successfully");
    }
}

package com.paschal.blogTask.controller;


import com.paschal.blogTask.dto.request.LoginDto;
import com.paschal.blogTask.dto.request.SignUpDto;
import com.paschal.blogTask.dto.response.UserResponseDto;
import com.paschal.blogTask.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class is a Spring Rest Controller that handles user login and sign-up operations.
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginAndSignUpController {

    // The UserService is injected into this controller for user-related operations.
    private final UserService userService;

    // This method handles the HTTP POST request for user sign-up.
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody SignUpDto signupDto) {
        // Call the userService to register a new user and return the response.
        return ResponseEntity.ok(userService.registerNewUser(signupDto));
    }

    // This method handles the HTTP POST request for user login.
    @PostMapping(path = "/login", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<UserResponseDto> loginUser(@Valid @RequestBody LoginDto loginDto) {
        // Call the userService to log in a user and return the response.
        return ResponseEntity.ok(userService.loginUser(loginDto));
    }
}
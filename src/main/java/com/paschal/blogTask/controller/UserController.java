package com.paschal.blogTask.controller;

import com.paschal.blogTask.dto.request.UserDto;
import com.paschal.blogTask.dto.response.UserResponseDto;
import com.paschal.blogTask.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// This class is a Spring Rest Controller that handles user-related operations.
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class UserController {

    // The UserService is injected into this controller for user-related operations.
    private final UserService userService;

    // This method handles the HTTP DELETE request for removing a user by their ID.
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable("userId") Long userId) {
        try {
            // Call the userService to delete the user by their ID.
            userService.deleteUser(userId);
            // If successful, return a 200 OK response with a success message.
            return ResponseEntity.ok("User with ID " + userId + " deleted.");
        } catch (Exception e) {
            // If an exception occurs (e.g., user not found), return a 500 Internal Server Error response
            // with an error message.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete user with ID because it is not found " + userId);
        }
    }
}


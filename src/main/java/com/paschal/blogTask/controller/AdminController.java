package com.paschal.blogTask.controller;

import com.paschal.blogTask.dto.request.AdminDto;
import com.paschal.blogTask.dto.request.AdminLoginDto;
import com.paschal.blogTask.dto.response.*;
import com.paschal.blogTask.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// This class is a Spring Rest Controller that handles admin-related operations.
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    // The AdminService is injected into this controller for admin-related operations.
    private final AdminService adminService;

    // This method handles the HTTP POST request to register a new admin.
    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<AdminResponseDto> registerAdmin(@Valid @RequestBody AdminDto adminDto) {
        // Call the adminService to register a new admin and return the response.
        return ResponseEntity.ok(adminService.RegisterNewAdmin(adminDto));
    }

    // This method handles the HTTP POST request for admin login.
    @PostMapping("/login")
    public ResponseEntity<AdminResponseDto> loginAdmin(@Valid @RequestBody AdminLoginDto adminDto) {
        // Call the adminService to log in an admin and return the response.
        return ResponseEntity.ok(adminService.loginAdmin(adminDto));
    }

    // This method handles the HTTP GET request to get all posts by the admin.
    @GetMapping("/posts")
    public ResponseEntity<List<PostResponseDto>> getAllPostsByAdmin() {
        // Call the adminService to get all posts and return the response.
        return ResponseEntity.ok(adminService.getAllPosts());
    }

    // This method handles the HTTP GET request to get all comments by the admin.
    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllCommentsByAdmin() {
        // Call the adminService to get all comments and return the response.
        return ResponseEntity.ok(adminService.getAllComments());
    }

    // This method handles the HTTP GET request to get all likes by the admin.
    @GetMapping("/likes")
    public ResponseEntity<List<LikeResponseDto>> getAllLikesByAdmin() {
        // Call the adminService to get all likes and return the response.
        return ResponseEntity.ok(adminService.getAllLikes());
    }

    // This method handles the HTTP DELETE request to delete a user by the admin.
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<String> deleteUserByAdmin(@PathVariable("userId") Long userId) {
        try {
            // Call the adminService to delete a user and return a success response.
            adminService.deleteUser(userId);
            return ResponseEntity.ok("User with id " + userId + " deleted");
        } catch (Exception e) {
            // If an exception occurs (e.g., user not found), return a 500 Internal Server Error response
            // with an error message.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("You can't delete user with id " + userId + " because it does not exist");
        }
    }

    // This method handles the HTTP DELETE request to delete a post by the admin.
    @DeleteMapping("/delete-post/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable("postId") Long postId) {
        try {
            // Call the adminService to delete a post and return a success response.
            adminService.DeletePost(postId);
            return ResponseEntity.ok("Post with id " + postId + " deleted");
        } catch (Exception e) {
            // If an exception occurs (e.g., post not found), return a 500 Internal Server Error response
            // with an error message.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("You can't delete post with id " + postId + " because it does not exist");
        }
    }

    // This method handles the HTTP DELETE request to delete a comment by the admin.
    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<String> deleteCommentByAdmin(@PathVariable("commentId") Long commentId) {
        try {
            // Call the adminService to delete a comment and return a success response.
            adminService.DeleteComment(commentId);
            return ResponseEntity.ok("Comment with id + " + commentId + " deleted");
        } catch (Exception e) {
            // If an exception occurs (e.g., comment not found), return a 500 Internal Server Error response
            // with an error message.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("You can't delete comment with id " + commentId + " because it does not exist");
        }
    }

    // This method handles the HTTP DELETE request to delete a like by the admin.
    @DeleteMapping("/delete-like/{likeId}")
    public ResponseEntity<String> deleteLikeByAdmin(@PathVariable("likeId") Long likeId) {
        try {
            // Call the adminService to delete a like and return a success response.
            adminService.DeleteLike(likeId);
            return ResponseEntity.ok("Like with id " + likeId + " deleted");
        } catch (Exception e) {
            // If an exception occurs (e.g., like not found), return a 500 Internal Server Error response
            // with an error message.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("You can't delete like with id " + likeId + " because it does not exist");
        }
    }
}
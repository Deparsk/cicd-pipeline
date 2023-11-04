package com.paschal.blogTask.controller;

import com.paschal.blogTask.dto.response.LikeResponseDto;
import com.paschal.blogTask.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class is a Spring Rest Controller that handles like-related operations.
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LikeController {

    // The LikeService is injected into this controller for like-related operations.
    private final LikeService likeService;

    // This method handles the HTTP POST request to like a post by a user.
    @PostMapping("/like/{userId}/{postId}")
    public ResponseEntity<LikeResponseDto> likePost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        // Call the likeService to like a post and return the response.
        return ResponseEntity.ok(likeService.likePost(userId, postId));
    }

    // This method handles the HTTP GET request to get all likes by a user for a specific post.
    @GetMapping("/likes/{userId}/{postId}")
    public ResponseEntity<List<LikeResponseDto>> getAllLikeByUser(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        // Call the likeService to get all likes by a user for a specific post and return the response.
        return ResponseEntity.ok(likeService.getAllLikesByUser(userId, postId));
    }

    // This method handles the HTTP DELETE request to unlike a post by a user.
    @DeleteMapping("/unlike/{userId}/{postId}/{likeId}")
    public ResponseEntity<String> unLikePost(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId, @PathVariable("likeId") Long likeId) {
        try {
            // Call the likeService to unlike a post and return a success response.
            likeService.unLikePost(userId, postId, likeId);
            return ResponseEntity.ok("Like with likeId " + likeId + " deleted");
        } catch (Exception e) {
            // If an exception occurs (e.g., like not found), return a 500 Internal Server Error response
            // with an error message.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Like with likeId " + likeId + " can't be deleted because it does not exist");
        }
    }
}

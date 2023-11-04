package com.paschal.blogTask.controller;

import com.paschal.blogTask.dto.request.PostDto;
import com.paschal.blogTask.dto.response.PostPageResponseDto;
import com.paschal.blogTask.dto.response.PostResponseDto;
import com.paschal.blogTask.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// This class is a Spring Rest Controller that handles post-related operations for a specific user.
@RestController
@RequestMapping("/api/v1/user/{userId}")
public class PostController {

    // The PostService is injected into this controller for post-related operations.
    private final PostService postService;

    // Constructor to inject the PostService into the controller.
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // This method handles the HTTP POST request to create a new post for a specific user.
    @PostMapping("/create-post")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostDto postDto, @PathVariable Long userId) {
        // Call the postService to create a new post and return the response.
        return ResponseEntity.ok(postService.createPost(postDto, userId));
    }

    // This method handles the HTTP DELETE request to remove a post by its ID.
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<String> removePost(@PathVariable("postId") Long postId) {
        try {
            // Call the postService to delete the post by its ID.
            postService.deletePost(postId);
            // If successful, return a 200 OK response with a success message.
            return ResponseEntity.ok("Post with ID " + postId + " deleted.");
        } catch (Exception e) {
            // If an exception occurs (e.g., post not found), return a 500 Internal Server Error response
            // with an error message.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete post with ID " + postId);
        }
    }
}


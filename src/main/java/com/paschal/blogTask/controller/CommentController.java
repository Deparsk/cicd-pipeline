package com.paschal.blogTask.controller;

import com.paschal.blogTask.dto.request.CommentDto;
import com.paschal.blogTask.dto.response.CommentResponseDto;
import com.paschal.blogTask.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This class is a Spring Rest Controller that handles comment-related operations for a specific post and user.
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/{userId}/post/{postId}")
public class CommentController {

    // The CommentService is injected into this controller for comment-related operations.
    private final CommentService commentService;

    // This method handles the HTTP POST request to create a new comment for a specific user and post.
    @PostMapping("/create-comment")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable("userId") Long userId,
                                                            @PathVariable("postId") Long postId,
                                                            @Valid @RequestBody CommentDto commentDto) {
        // Call the commentService to create a new comment and return the response.
        return ResponseEntity.ok(commentService.createComment(userId, postId, commentDto));
    }

    // This method handles the HTTP GET request to get all comments for a specific post and user.
    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponseDto>> getAllComments(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId) {
        // Call the commentService to get all comments for a specific post and user and return the response.
        return ResponseEntity.ok(commentService.getAllComments(userId, postId));
    }

    // This method handles the HTTP GET request to get a specific comment by its ID for a specific post and user.
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<CommentResponseDto> getCommentByUser(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        // Call the commentService to get a specific comment by its ID for a specific post and user and return the response.
        return ResponseEntity.ok(commentService.getCommentById(userId, postId, commentId));
    }

    // This method handles the HTTP DELETE request to remove a comment by its ID for a specific post and user.
    @DeleteMapping("/delete-comment/{commentId}")
    public ResponseEntity<String> removeComment(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        try {
            // Call the commentService to delete the comment and return a success response.
            commentService.deleteComment(userId, postId, commentId);
            return ResponseEntity.ok("Comment with ID " + commentId + " deleted.");
        } catch (Exception e) {
            // If an exception occurs (e.g., comment not found), return a 500 Internal Server Error response
            // with an error message.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete comment with ID " + commentId);
        }
    }
}
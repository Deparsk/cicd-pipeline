package com.paschal.blogTask.service;

import com.paschal.blogTask.dto.response.CommentResponseDto;
import com.paschal.blogTask.dto.request.CommentDto;

import java.util.List;

public interface CommentService {

    // Add a comment to a post
    CommentResponseDto createComment(Long userId, Long postId, CommentDto commentDto);

    // Get all Comments by Post ID
    List<CommentResponseDto> getAllComments(Long userId, Long postId);

    // Get Comments by ID
    CommentResponseDto getCommentById( Long userId, Long postId, Long commentId);

    // Delete a comment
    void deleteComment(Long commentId, Long postId, Long userId);

//    Comment createComment(Long postId, Long userId, String text);
//
//    void deleteComment(Long commentId);
//
//    List<CommentDto> getCommentsById(Long userId);
//
//    List<Comment> getAllComments();

}

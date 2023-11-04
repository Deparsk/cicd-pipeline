package com.paschal.blogTask.service.implementations;

import com.paschal.blogTask.dto.response.CommentResponseDto;
import com.paschal.blogTask.dto.request.CommentDto;
import com.paschal.blogTask.model.entity.Comment;
import com.paschal.blogTask.model.entity.Post;
import com.paschal.blogTask.model.entity.User;
import com.paschal.blogTask.exception.CommentNotFoundException;
import com.paschal.blogTask.exception.PostNotFoundException;
import com.paschal.blogTask.exception.UnauthorizedUserException;
import com.paschal.blogTask.exception.UserNotFoundException;
import com.paschal.blogTask.repository.CommentRepository;
import com.paschal.blogTask.repository.PostRepository;
import com.paschal.blogTask.repository.UserRepository;
import com.paschal.blogTask.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// This class is marked as a service.
@Service
public class CommentServiceImplementation implements CommentService {

    // The CommentRepository, UserRepository, and PostRepository are used for database operations.
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper mapper = new ModelMapper();

    // Constructor for CommentServiceImplementation, with dependencies injected.
    @Autowired
    public CommentServiceImplementation(
            CommentRepository commentRepository,
            UserRepository userRepository,
            PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    // Implementation of the createComment method.
    @Override
    public CommentResponseDto createComment(Long userId, Long postId, CommentDto commentDto) {

        // Find the post by postId or throw a PostNotFoundException if not found.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not Found with id, " + postId));

        // Find the user by userId or throw a UserNotFoundException if not found.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with userId " + userId));

        // Create a new Comment object and save it.
        Comment newComment = Comment.builder()
                .postEntity(post)
                .userEntity(user)
                .comment(commentDto.getComment())
                .build();

        Comment savedComment = commentRepository.save(newComment);

        // Map and return the saved Comment as a CommentResponseDto.
        return CommentResponseDto.builder()
                .commentId(savedComment.getCommentId())
                .comment(savedComment.getComment())
                .commentDate(savedComment.getCommentDate())
                .postId(savedComment.getPostEntity().getPostId())
                .userId(savedComment.getUserEntity().getUserId())
                .build();
    }

    // Implementation of the getAllComments method.
    @Override
    public List<CommentResponseDto> getAllComments(Long userId, Long postId) {

        // Find the post by postId or throw a PostNotFoundException if not found.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not Found with id, " + postId));

        // Check if the current user is authorized to access the comments for this post.
        if (!post.getUserEntity().getUserId().equals(userId)) {
            throw new UnauthorizedUserException("You are not authorized to access comments for this post.");
        }

        // Find all comments for the post.
        List<Comment> comments = commentRepository.findAllByPostEntity(post);

        // Create a list to store CommentResponseDto objects.
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        // Map and add each comment to the response list.
        for (Comment comment : comments) {
            CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                    .commentId(comment.getCommentId())
                    .comment(comment.getComment())
                    .commentDate(comment.getCommentDate())
                    .postId(comment.getPostEntity().getPostId())
                    .userId(comment.getUserEntity().getUserId())
                    .build();
            commentResponseDtoList.add(commentResponseDto);
        }

        // Return the list of CommentResponseDto objects.
        return commentResponseDtoList;
    }

    // Implementation of the getCommentById method.
    @Override
    public CommentResponseDto getCommentById(Long userId, Long postId, Long commentId) {

        // Find the post by postId or throw a PostNotFoundException if not found.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not Found with id, " + postId));

        // Find the comment by commentId or throw a CommentNotFoundException if not found.
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with commentId " + commentId));

        // Check if the current user is authorized to access this comment.
        if (!post.getUserEntity().getUserId().equals(userId) && !comment.getUserEntity().getUserId().equals(userId)) {
            throw new UnauthorizedUserException("You are not authorized to access this comment.");
        }

        // Map and return the comment as a CommentResponseDto.
        return CommentResponseDto.builder()
                .commentId(comment.getCommentId())
                .comment(comment.getComment())
                .commentDate(comment.getCommentDate())
                .postId(comment.getPostEntity().getPostId())
                .userId(comment.getUserEntity().getUserId())
                .build();
    }

    // Implementation of the deleteComment method.
    @Override
    public void deleteComment(Long commentId, Long postId, Long userId) {

        // Find the comment by commentId.
        Optional<Comment> optionalComment = commentRepository.findById(commentId);

        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();

            // Check if the user is authorized to delete this comment.
            if (!existingComment.getPostEntity().getPostId().equals(postId) && !existingComment.getUserEntity().getUserId().equals(userId)) {
                throw new UnauthorizedUserException("You are not authorized to delete this comment.");
            } else {
                // Delete the comment.
                commentRepository.deleteById(commentId);
            }
        } else {
            // If the comment doesn't exist, throw a CommentNotFoundException.
            throw new CommentNotFoundException("Comment not found with commentId " + commentId);
        }
    }
}
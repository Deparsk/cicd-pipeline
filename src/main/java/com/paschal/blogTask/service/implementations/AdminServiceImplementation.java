package com.paschal.blogTask.service.implementations;

import com.paschal.blogTask.dto.request.AdminDto;
import com.paschal.blogTask.dto.request.AdminLoginDto;
import com.paschal.blogTask.dto.response.AdminResponseDto;
import com.paschal.blogTask.dto.response.CommentResponseDto;
import com.paschal.blogTask.dto.response.LikeResponseDto;
import com.paschal.blogTask.dto.response.PostResponseDto;
import com.paschal.blogTask.exception.*;
import com.paschal.blogTask.model.entity.Admin;
import com.paschal.blogTask.model.entity.Comment;
import com.paschal.blogTask.model.entity.Like;
import com.paschal.blogTask.model.entity.Post;
import com.paschal.blogTask.repository.*;
import com.paschal.blogTask.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
// This class is marked as a service and uses Lombok's @RequiredArgsConstructor to generate constructor injection.
@RequiredArgsConstructor
@Service
public class AdminServiceImplementation implements AdminService {

    // The AdminRepository, UserRepository, PostRepository, CommentRepository, and LikeRepository are used for database operations.
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;
    private final ModelMapper mapper = new ModelMapper();

    // Implementation of the RegisterNewAdmin method.
    @Override
    public AdminResponseDto RegisterNewAdmin(AdminDto adminDto) {

        // Check if the provided email already exists in the database.
        if (adminRepository.existsByEmail(adminDto.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        // Check if the password and confirm password match.
        if (!adminDto.getPassword().equals(adminDto.getConfirmPassword())) {
            throw new PasswordMisMatchException("Password mismatch; please try again.");
        }

        // Create a new Admin object and set the role to "ADMIN."
        Admin newAdmin = mapper.map(adminDto, Admin.class);
        newAdmin.setRole("ADMIN");

        // Save the new Admin to the database.
        Admin savedAdmin = adminRepository.save(newAdmin);

        // Map and return the saved Admin as an AdminResponseDto.
        return mapper.map(savedAdmin, AdminResponseDto.class);
    }

    // Implementation of the loginAdmin method.
    @Override
    public AdminResponseDto loginAdmin(AdminLoginDto adminDto) {

        // Find an admin by email and password or throw a WrongDetailsException if not found.
        Admin admin = adminRepository.findByEmailAndPassword(adminDto.getEmail(), adminDto.getPassword())
                .orElseThrow(() -> new WrongDetailsException("Email or password incorrect"));

        // Map and return the found admin as an AdminResponseDto.
        return mapper.map(admin, AdminResponseDto.class);
    }

    // Implementation of the getAllPosts method.
    @Override
    public List<PostResponseDto> getAllPosts() {

        // Find all posts in the database.
        List<Post> posts = postRepository.findAll();

        // Map and return the list of posts as PostResponseDto objects using streams.
        return posts.stream()
                .map(post -> mapper.map(post, PostResponseDto.class))
                .collect(Collectors.toList());
    }

    // Implementation of the getAllComments method.
    @Override
    public List<CommentResponseDto> getAllComments() {

        // Find all comments in the database.
        List<Comment> comments = commentRepository.findAll();

        // Map and return the list of comments as CommentResponseDto objects using streams.
        return comments.stream()
                .map(comment -> mapper.map(comment, CommentResponseDto.class))
                .collect(Collectors.toList());
    }

    // Implementation of the getAllLikes method.
    @Override
    public List<LikeResponseDto> getAllLikes() {

        // Find all likes in the database.
        List<Like> likes = likeRepository.findAll();

        // Map and return the list of likes as LikeResponseDto objects using streams.
        return likes.stream()
                .map(like -> mapper.map(like, LikeResponseDto.class))
                .collect(Collectors.toList());
    }

    // Implementation of the deleteUser method.
    @Override
    public void deleteUser(Long userId) {

        // Check if the user with the provided userId exists; if not, throw a UserNotFoundException.
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with id " + userId + " does not exist.");
        }

        // Delete the user by userId.
        userRepository.deleteById(userId);
    }

    // Implementation of the DeletePost method.
    @Override
    public void DeletePost(Long postId) {

        // Check if the post with the provided postId exists; if not, throw a PostNotFoundException.
        if (!postRepository.existsById(postId)) {
            throw new PostNotFoundException("Post with id " + postId + " does not exist.");
        }

        // Delete the post by postId.
        postRepository.deleteById(postId);
    }

    // Implementation of the DeleteComment method.
    @Override
    public void DeleteComment(Long commentId) {

        // Check if the comment with the provided commentId exists; if not, throw a CommentNotFoundException.
        if (!commentRepository.existsById(commentId)) {
            throw new CommentNotFoundException("Comment with id " + commentId + " does not exist.");
        }

        // Delete the comment by commentId.
        commentRepository.deleteById(commentId);
    }

    // Implementation of the DeleteLike method.
    @Override
    public void DeleteLike(Long likeId) {

        // Check if the like with the provided likeId exists; if not, throw a NoExistingLikeException.
        if (!likeRepository.existsById(likeId)) {
            throw new NoExistingLikeException("Like with id " + likeId + " does not exist.");
        }

        // Delete the like by likeId.
        likeRepository.deleteById(likeId);
    }
}
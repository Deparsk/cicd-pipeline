package com.paschal.blogTask.service.implementations;

import com.paschal.blogTask.dto.request.PostDto;
import com.paschal.blogTask.dto.response.PostResponseDto;
import com.paschal.blogTask.model.entity.Post;
import com.paschal.blogTask.model.entity.User;
import com.paschal.blogTask.model.error.ErrorModel;
import com.paschal.blogTask.exception.UserNotFoundException;
import com.paschal.blogTask.repository.PostRepository;
import com.paschal.blogTask.repository.UserRepository;
import com.paschal.blogTask.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// This class is marked as a service and uses Lombok's @RequiredArgsConstructor to generate constructor injection.
@RequiredArgsConstructor
@Service
public class PostServiceImplementation implements PostService {

    // ModelMapper is used for mapping data between different models.
    private final ModelMapper mapper = new ModelMapper();

    // The PostRepository and UserRepository are used for database operations.
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // Implementation of the createPost method.
    @Override
    public PostResponseDto createPost(PostDto postDto, Long userId) {

        // Find a user in the database by userId.
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            // If the user exists, create a new Post.
            Post newPost = mapper.map(postDto, Post.class);

            // Set the UserEntity for the new Post.
            newPost.setUserEntity(userRepository.findById(userId).get());

            // Save the new Post to the database.
            Post savePost = postRepository.save(newPost);

            // Build and return a PostResponseDto.
            return PostResponseDto.builder()
                    .postId(savePost.getPostId())
                    .createdDate(savePost.getCreatedDate())
                    .content(savePost.getContent())
                    .title(savePost.getTitle())
                    .userId(savePost.getUserEntity().getUserId())
                    .build();
        } else {
            // If the user doesn't exist, create an error model and throw a UserNotFoundException.
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_ID_NOT_EXIST");
            errorModel.setMessage("User does not exist");
            errorModelList.add(errorModel);
            throw new UserNotFoundException(errorModelList.toString());
        }
    }

    // Implementation of the deletePost method.
    @Override
    public void deletePost(Long postId) {

        // Delete a post by its ID.
        postRepository.deleteById(postId);
    }
}
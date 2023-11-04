package com.paschal.blogTask.service.implementations;

import com.paschal.blogTask.dto.response.LikeResponseDto;
import com.paschal.blogTask.model.entity.Like;
import com.paschal.blogTask.model.entity.Post;
import com.paschal.blogTask.model.entity.User;
import com.paschal.blogTask.exception.*;
import com.paschal.blogTask.repository.LikeRepository;
import com.paschal.blogTask.repository.PostRepository;
import com.paschal.blogTask.repository.UserRepository;
import com.paschal.blogTask.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



// This class is marked as a service and uses Lombok's @RequiredArgsConstructor to generate constructor injection.
@RequiredArgsConstructor
@Service
public class LikeServiceImplementation implements LikeService {

    // The LikeRepository, PostRepository, and UserRepository are used for database operations.
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    // Implementation of the likePost method.
    @Override
    public LikeResponseDto likePost(Long userId, Long postId) {

        // Find the user by userId or throw a UserNotFoundException if not found.
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with userId " + userId));

        // Find the post by postId or throw a PostNotFoundException if not found.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with postId " + postId));

        // Check if the user has already liked this post.
        Like existingLike = likeRepository.findByPostPostIdAndUserUserId(postId, userId);
        if (existingLike != null) {
            throw new LikeAlreadyExistException("User has already liked this post.");
        }

        // Create a new Like object and save it.
        Like newLike = Like.builder()
                .user(user)
                .post(post)
                .build();
        likeRepository.save(newLike);

        // Map and return the new Like as a LikeResponseDto.
        return mapper.map(newLike, LikeResponseDto.class);
    }

    // Implementation of the getAllLikesByUser method.
    @Override
    public List<LikeResponseDto> getAllLikesByUser(Long userId, Long postId) {

        // Find the post by postId or throw a PostNotFoundException if not found.
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with the postId " + postId));

        // Check if the current user is authorized to access the likes for this post.
        if (!post.getUserEntity().getUserId().equals(userId)) {
            throw new UnauthorizedUserException("You are not authorized to access Likes for this post.");
        }

        // Find all likes for the post.
        List<Like> likes = likeRepository.findAllByPost(post);

        // Create a list to store LikeResponseDto objects.
        List<LikeResponseDto> likeResponseDtoList = new ArrayList<>();

        // Map and add each like to the response list.
        for (Like like : likes) {
            LikeResponseDto likeResponseDto = LikeResponseDto.builder()
                    .likeId(like.getLikeId())
                    .postId(like.getPost().getPostId())
                    .userId(like.getUser().getUserId())
                    .build();
            likeResponseDtoList.add(likeResponseDto);
        }

        // Return the list of LikeResponseDto objects.
        return likeResponseDtoList;
    }

    // Implementation of the unLikePost method.
    @Override
    public void unLikePost(Long userId, Long postId, Long likeId) {

        // Check if the user has already liked this post; if not, throw a NoExistingLikeException.
        Like existingLike = likeRepository.findByPostPostIdAndUserUserId(postId, userId);
        if (existingLike == null) {
            throw new NoExistingLikeException("User has not liked this post.");
        }

        // Delete the like by its likeId.
        likeRepository.deleteById(likeId);
    }
}
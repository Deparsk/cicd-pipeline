package com.paschal.blogTask.service;


import com.paschal.blogTask.dto.request.PostDto;
import com.paschal.blogTask.dto.response.PostResponseDto;

public interface PostService {


    // Create a new post
    PostResponseDto createPost (PostDto postDto, Long userId);

    // Delete a post by its ID
    void deletePost(Long postId);

    // Get a post by ID
   // Post getPostById(Long postId);


    // Get all posts
   // List<Post> getAllPosts();

   // List<PostDTO> getLikedPostsByUser(Long userId);

   // List<PostDTO> getCommentedPostsByUser(Long userId);

}

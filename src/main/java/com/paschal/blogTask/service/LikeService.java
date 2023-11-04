package com.paschal.blogTask.service;

import com.paschal.blogTask.dto.response.LikeResponseDto;

import java.util.List;

public interface LikeService {

    // Add a like to a post
    LikeResponseDto likePost(Long userId, Long postId);

    // Get all likes by a User
    List<LikeResponseDto> getAllLikesByUser(Long userId,Long postId);

    //Unlike a post
    void unLikePost(Long likeId, Long postId, Long userId);






}

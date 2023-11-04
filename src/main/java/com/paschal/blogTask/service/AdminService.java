package com.paschal.blogTask.service;

import com.paschal.blogTask.dto.request.AdminDto;
import com.paschal.blogTask.dto.request.AdminLoginDto;
import com.paschal.blogTask.dto.response.*;
import com.paschal.blogTask.model.entity.Comment;
import com.paschal.blogTask.model.entity.Like;
import com.paschal.blogTask.model.entity.Post;
import com.paschal.blogTask.model.entity.User;

import java.util.List;


public interface AdminService {
    AdminResponseDto RegisterNewAdmin(AdminDto adminDto);
    AdminResponseDto loginAdmin(AdminLoginDto adminDto);

    List<PostResponseDto> getAllPosts();
    List<CommentResponseDto> getAllComments();
    List<LikeResponseDto> getAllLikes();
    void deleteUser(Long userId);
    void DeletePost(Long postId);
    void DeleteComment(Long commentId);
    void DeleteLike(Long likeId);
}


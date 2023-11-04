package com.paschal.blogTask.repository;

import com.paschal.blogTask.model.entity.Like;
import com.paschal.blogTask.model.entity.Post;
import com.paschal.blogTask.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByUser_UserId(Long userId);

    Like findByPostAndUser(Post post, User user);

    Like findByPostPostIdAndUserUserId(Long postId, Long userId);

    List<Like> findAllByPost(Post post);

    // You don't need to implement methods here; JpaRepository provides basic CRUD operations.

}


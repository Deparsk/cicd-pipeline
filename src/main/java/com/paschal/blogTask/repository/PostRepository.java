package com.paschal.blogTask.repository;

import com.paschal.blogTask.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
   // List<Post> findCommentedPostsByUserId(Long userId);

   // List<Post> findLikedPostsByUserId(Long userId);

    // You don't need to implement methods here; JpaRepository provides basic CRUD operations.

}

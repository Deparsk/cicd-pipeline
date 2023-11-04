package com.paschal.blogTask.repository;

import com.paschal.blogTask.model.entity.Comment;
import com.paschal.blogTask.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostEntity(Post post);

    // You don't need to implement methods here; JpaRepository provides basic CRUD operations.

}

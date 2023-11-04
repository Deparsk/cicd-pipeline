package com.paschal.blogTask.repository;

import com.paschal.blogTask.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Boolean existsByEmail(String email);

    Optional<Admin> findByEmailAndPassword(String email, String password);
}


package com.paschal.blogTask.service;

import com.paschal.blogTask.dto.request.LoginDto;
import com.paschal.blogTask.dto.request.SignUpDto;
import com.paschal.blogTask.dto.response.UserResponseDto;
import com.paschal.blogTask.model.entity.User;

public interface UserService {

    // Register a new user
    UserResponseDto registerNewUser(SignUpDto signupDto);


        // User Login
    UserResponseDto loginUser(LoginDto loginDto);



    // delete a User
    void deleteUser(Long userId);


}

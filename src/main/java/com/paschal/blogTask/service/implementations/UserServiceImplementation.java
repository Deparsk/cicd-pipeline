package com.paschal.blogTask.service.implementations;


import com.paschal.blogTask.dto.request.LoginDto;
import com.paschal.blogTask.dto.request.SignUpDto;
import com.paschal.blogTask.dto.response.UserResponseDto;
import com.paschal.blogTask.model.entity.User;
import com.paschal.blogTask.exception.*;
import com.paschal.blogTask.repository.UserRepository;
import com.paschal.blogTask.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// This class is marked as a service and will be managed by Spring Framework.
@Transactional
@Service
public class UserServiceImplementation implements UserService {

    // The UserRepository is used for database operations.
    private final UserRepository userRepository;

    // ModelMapper is used for mapping data between different models.
    private final ModelMapper mapper = new ModelMapper();

    // Constructor for UserServiceImplementation, with UserRepository injected.
    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Implementation of the registerNewUser method.
    @Override
    public UserResponseDto registerNewUser(SignUpDto signupDto) {

        // Check if the provided email already exists in the database.
        if (userRepository.existsByEmail(signupDto.getEmail())) {
            // If it exists, throw a DuplicateEmailException.
            throw new DuplicateEmailException("Email already exists!");
        }

        // Check if the password and confirm password match.
        if (!signupDto.getPassword().equals(signupDto.getConfirmPassword())) {
            // If they don't match, throw a PasswordMisMatchException.
            throw new PasswordMisMatchException("Password mismatch; please try again.");
        }

        // Map the SignUpDto to a User object and set the role to "USER."
        User newUser = mapper.map(signupDto, User.class);
        newUser.setRole("USER");

        // Save the new user to the database.
        User savedUser = userRepository.save(newUser);

        // Map the saved user to a UserResponseDto and return it.
        return mapper.map(savedUser, UserResponseDto.class);
    }

    // Implementation of the loginUser method.
    @Override
    public UserResponseDto loginUser(LoginDto loginDto) {

        // Find a user in the database by email and password.
        User user = userRepository
                .findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword())
                .orElseThrow(() -> new PasswordMisMatchException("Email or password incorrect"));

        // Map the user to a UserResponseDto and return it.
        return mapper.map(user, UserResponseDto.class);
    }

    // Implementation of the deleteUser method.
    @Override
    public void deleteUser(Long userId) {

        // Check if the user with the provided userId exists.
        if (!userRepository.existsById(userId)) {
            // If the user doesn't exist, throw a UserNotFoundException.
            throw new UserNotFoundException("User not found with id: " + userId);
        }

        // Delete the user with the provided userId.
        userRepository.deleteById(userId);
    }
}
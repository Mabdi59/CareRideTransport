package com.careridetransport.server.service;

import com.careridetransport.server.model.User;
import com.careridetransport.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User registerUser(User user) {
        // Hash the password before saving the user
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // Retrieve a user by their ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update a user's profile
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setFullName(updatedUser.getFullName());
            user.setPhoneNumber(updatedUser.getPhoneNumber());
            // More fields to update as needed
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // Delete a user by their ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Authenticate a user
    public boolean authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return bCryptPasswordEncoder.matches(password, user.get().getPassword());
        } else {
            return false;
        }
    }
}

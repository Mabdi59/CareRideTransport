package com.careride.transportbackend.service;

import com.careride.transportbackend.entity.User;
import com.careride.transportbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUser(Long userId, User user) {
        User existingUser = getUserById(userId);
        if (existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            existingUser.setRoles(user.getRoles());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public boolean isUsernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        User existingUser = findByUsername(username);

        if (existingUser != null && passwordEncoder.matches(password, existingUser.getPassword())) {
            return existingUser;
        }

        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
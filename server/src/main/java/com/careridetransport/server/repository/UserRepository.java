package com.careridetransport.server.repository;

import com.careridetransport.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    // Custom query methods can be declared here, if needed

    // Example: Find a user by their email
    // User findByEmail(String email);
}

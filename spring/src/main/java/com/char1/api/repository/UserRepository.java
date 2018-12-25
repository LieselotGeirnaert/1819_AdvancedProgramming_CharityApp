package com.char1.api.repository;

import com.char1.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmailAddress(String emailAddress);
    boolean existsByEmailAddress(String emailAddress);
}
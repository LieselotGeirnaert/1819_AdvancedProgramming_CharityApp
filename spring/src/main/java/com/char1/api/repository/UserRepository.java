package com.char1.api.repository;

import com.char1.api.entity.Charity;
import com.char1.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmailAddress(String emailAddress);
}
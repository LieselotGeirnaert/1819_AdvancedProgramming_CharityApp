package com.char1.api.repository;

import com.char1.api.entity.User;
import com.char1.api.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChallengeRepository extends JpaRepository<UserChallenge, String> {
    UserChallenge findById(int id);
    List<UserChallenge> findAllByUser(User user);
}

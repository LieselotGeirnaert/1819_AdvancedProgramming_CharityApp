package com.char1.api.repository;

import com.char1.api.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, String> {
    Challenge findById(int id);
}

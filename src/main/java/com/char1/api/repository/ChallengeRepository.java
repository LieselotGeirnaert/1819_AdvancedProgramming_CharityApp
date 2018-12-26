package com.char1.api.repository;

import com.char1.api.entity.Category;
import com.char1.api.entity.Challenge;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengeRepository extends JpaRepository<Challenge, Integer> {
    Challenge findById(int id);
    List<Challenge> findByCategory(Category category);
}

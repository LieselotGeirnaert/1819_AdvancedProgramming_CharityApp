package com.char1.api.repository;

import com.char1.api.entity.Progress;
import com.char1.api.entity.TotalProgress;
import com.char1.api.entity.UserChallenge;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalProgressRepository extends JpaRepository<TotalProgress, Integer> {
    List<TotalProgress> findAllTotalProgressByUserChallenge(UserChallenge userChallenge);
    TotalProgress findTotalProgressById(int id);
}
package com.char1.api.repository;

import com.char1.api.entity.DailyProgress;
import com.char1.api.entity.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyProgressRepository extends JpaRepository<DailyProgress, Integer>{
    List<DailyProgress> findAllDailyProgressByUserChallenge(UserChallenge userChallenge);
    DailyProgress findDailyProgressById(int id);

}

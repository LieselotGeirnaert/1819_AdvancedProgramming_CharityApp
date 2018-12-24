package com.char1.api.service;

import com.char1.api.controller.exception.UserChallengeDateTimeExeption;
import com.char1.api.entity.DailyProgress;
import com.char1.api.entity.Progress;
import com.char1.api.entity.TotalProgress;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.DailyProgressRepository;
import com.char1.api.repository.TotalProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    TotalProgressRepository totalProgressRepository;

    @Autowired
    DailyProgressRepository dailyProgressRepository;

    public TotalProgress newTotalProgress(UserChallenge userChallenge) {

        TotalProgress totalProgress = new TotalProgress();

        totalProgress.setEntryDate(LocalDateTime.now());
        totalProgress.setUserChallenge(userChallenge);

        if (totalProgress.getCurrentAmount() == 0) {
            if (userChallenge.getStartDate() != null && userChallenge.getDeadlineDate() != null) {
                long daysToComplete = ChronoUnit.DAYS.between(userChallenge.getStartDate(), userChallenge.getDeadlineDate());
                totalProgress.setCurrentAmount(userChallenge.getAmountToComplete() / (int) daysToComplete);
            } else {
                throw new UserChallengeDateTimeExeption();
            }
        } else {
            totalProgress.setCurrentAmount(totalProgress.getCurrentAmount());
        }
        return totalProgressRepository.save(totalProgress);
    }

    public DailyProgress newDailyProgress(UserChallenge userChallenge) {

        DailyProgress dailyProgress = new DailyProgress();

        dailyProgress.setEntryDate(LocalDateTime.now());
        dailyProgress.setUserChallenge(userChallenge);
        dailyProgress.setCurrentAmount(1);

        return dailyProgressRepository.save(dailyProgress);
    }



}

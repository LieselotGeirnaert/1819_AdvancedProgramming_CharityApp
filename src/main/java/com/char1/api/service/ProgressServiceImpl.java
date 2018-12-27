package com.char1.api.service;

import com.char1.api.controller.exception.ProgressException;
import com.char1.api.controller.exception.UserChallengeDateTimeExeption;
import com.char1.api.entity.DailyProgress;
import com.char1.api.entity.Progress;
import com.char1.api.entity.TotalProgress;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.DailyProgressRepository;
import com.char1.api.repository.TotalProgressRepository;
import com.char1.api.utils.Char1Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    TotalProgressRepository totalProgressRepository;

    @Autowired
    DailyProgressRepository dailyProgressRepository;

    public TotalProgress newTotalProgress(UserChallenge userChallenge, int requestCurrentAmount) {

        TotalProgress totalProgress = new TotalProgress();

        totalProgress.setEntryDate(LocalDateTime.now());
        totalProgress.setUserChallenge(userChallenge);

        if (requestCurrentAmount == 0) {
            if (userChallenge.getStartDate() != null && userChallenge.getDeadlineDate() != null) {
                long daysToComplete = ChronoUnit.DAYS.between(userChallenge.getStartDate(), userChallenge.getDeadlineDate());
                totalProgress.setCurrentAmount(userChallenge.getAmountToComplete() / (int) daysToComplete);
            } else {
                throw new UserChallengeDateTimeExeption();
            }
        } else {
            totalProgress.setCurrentAmount(requestCurrentAmount);
        }
        return totalProgressRepository.save(totalProgress);
    }

    public DailyProgress newDailyProgress(UserChallenge userChallenge) {

        DailyProgress dailyProgress = new DailyProgress();

        dailyProgress.setEntryDate(LocalDateTime.now());
        dailyProgress.setUserChallenge(userChallenge);
        dailyProgress.setCurrentAmount(1);

        List<DailyProgress> dailyProgresses = userChallenge.getDailyProgress();
        dailyProgresses.add(dailyProgress);
        if (Char1Utils.calculateProgressFilteredOnDate(dailyProgresses, LocalDateTime.now().toLocalDate(), userChallenge.getAmountToCompleteDaily()) > 100)
            throw new ProgressException("ITS OVER A 100");


        return dailyProgressRepository.save(dailyProgress);
    }
}

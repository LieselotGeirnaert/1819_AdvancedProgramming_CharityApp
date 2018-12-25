package com.char1.api.utils;

import com.char1.api.entity.DailyProgress;
import com.char1.api.entity.TotalProgress;
import com.char1.api.entity.UserChallenge;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public final class Char1Utils {

    public static float calculateTotalProgress(UserChallenge userChallenge) {
        float currentAmount = 0;
        if (userChallenge.getTotalProgress() != null ){
            for (TotalProgress pr : userChallenge.getTotalProgress()) {
                currentAmount += pr.getCurrentAmount();
            }
            return (currentAmount / userChallenge.getAmountToComplete()) * 100;
        } else {
            return currentAmount;
        }
    }

    public static float calculateDailyProgress(UserChallenge userChallenge) {
        List<DailyProgress> dailyProgresses = userChallenge.getDailyProgress();
        float currentAmount = 0;
        if (dailyProgresses != null) {
            dailyProgresses = dailyProgresses.stream().filter(progress -> LocalDate.now().isEqual(progress.getEntryDate().toLocalDate())).collect(Collectors.toList());
            for (DailyProgress pr : dailyProgresses) {
                currentAmount += pr.getCurrentAmount();
            }
        return (currentAmount / userChallenge.getAmountToCompleteDaily()) * 100;
        } else {
            return currentAmount;
        }
    }
}

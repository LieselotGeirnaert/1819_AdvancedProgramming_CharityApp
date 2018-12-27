package com.char1.api.utils;

import com.char1.api.entity.DailyProgress;
import com.char1.api.entity.TotalProgress;
import com.char1.api.entity.UserChallenge;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Char1Utils {

    public static float calculateTotalProgress(UserChallenge userChallenge) {
        float currentAmount = 0;
        if (userChallenge.getTotalProgress() != null ){
            for (TotalProgress pr : userChallenge.getTotalProgress()) {
                currentAmount += pr.getCurrentAmount();
            }
            if (userChallenge.getAmountToComplete() == 0) {
                return (currentAmount / (ChronoUnit.DAYS.between(userChallenge.getStartDate(), userChallenge.getDeadlineDate()) + 1));
            } else {
                return (currentAmount / userChallenge.getAmountToComplete()) * 100;
            }
        } else {
            return currentAmount;
        }
    }

    public static float calculateDailyProgress(UserChallenge userChallenge) {
        List<DailyProgress> dailyProgresses = userChallenge.getDailyProgress();
        return calculateProgressFilteredOnDate(dailyProgresses, LocalDate.now(), userChallenge.getAmountToCompleteDaily());
    }

    public static HashMap<LocalDate, Float> returnDailyProgress(List<DailyProgress> dailyProgresses, int amountToCompleteDaily, LocalDate startDate) {
        HashMap<LocalDate,Float> dailyProgressPercentagePerDate = new HashMap<>();
        Set<LocalDate> setDates;
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, LocalDate.now()) + 1;
        setDates = IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toSet());
        setDates.forEach(d -> dailyProgressPercentagePerDate.put(d, calculateProgressFilteredOnDate(dailyProgresses, d, amountToCompleteDaily)));
        return dailyProgressPercentagePerDate;
    }

    private static float calculateProgressFilteredOnDate(List<DailyProgress> dailyProgresses, LocalDate localDate, int amountToCompleteDaily) {
        float currentAmount = 0;
        if (dailyProgresses != null) {
            dailyProgresses = dailyProgresses.stream().filter(progress -> localDate.isEqual(progress.getEntryDate().toLocalDate())).collect(Collectors.toList());
            for (DailyProgress pr : dailyProgresses) {
                currentAmount += pr.getCurrentAmount();
            }
            return (currentAmount / amountToCompleteDaily) * 100;
        } else {
            return currentAmount;
        }
    }

}

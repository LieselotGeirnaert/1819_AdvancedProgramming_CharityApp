package com.char1.api.service;

import com.char1.api.entity.DailyProgress;
import com.char1.api.entity.TotalProgress;
import com.char1.api.entity.UserChallenge;

public interface ProgressService {
    public TotalProgress newTotalProgress(UserChallenge userChallenge);
    public DailyProgress newDailyProgress(UserChallenge userChallenge);
}

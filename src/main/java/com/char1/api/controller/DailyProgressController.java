package com.char1.api.controller;

import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.entity.DailyProgress;
import com.char1.api.entity.TotalProgress;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.DailyProgressRepository;
import com.char1.api.repository.UserChallengeRepository;
import com.char1.api.request.DailyProgressRequest;
import com.char1.api.service.ProgressService;
import com.char1.api.service.UserChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/dailyprogress")
public class DailyProgressController {


    @Autowired
    DailyProgressRepository dailyProgressRepository;

    @Autowired
    UserChallengeRepository userChallengeRepository;

    @Autowired
    UserChallengeService userChallengeService;

    @Autowired
    ProgressService progressService;

    @PostMapping
    public DailyProgress createDailyProgress(OAuth2Authentication auth, @RequestBody DailyProgressRequest progressRequest) {
        UserChallenge userChallenge = userChallengeService.getUserChallengeByIdSecure(progressRequest.getUserChallengeId(), auth.getPrincipal().toString());
        DailyProgress dailyProgress = progressService.newDailyProgress(userChallenge);

        if (userChallenge.getDailyProgressPercentage() >= 100) {
            progressService.newTotalProgress(userChallenge, 0);
        }
        return dailyProgress;

    }

}

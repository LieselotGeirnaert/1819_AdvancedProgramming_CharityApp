package com.char1.api.controller;


import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.controller.exception.UnautherizedException;
import com.char1.api.controller.exception.UserChallengeDateTimeExeption;
import com.char1.api.entity.Progress;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.ProgressRepository;
import com.char1.api.repository.UserChallengeRepository;
import com.char1.api.request.ProgressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(value = "/progress")
public class ProgressController {

    @Autowired
    ProgressRepository progressRepository;
    
    @Autowired
    UserChallengeRepository userChallengeRepository;

    @GetMapping
    public List<Progress> getAllProgress() {
        return progressRepository.findAll();
    }
    
    @GetMapping(value = "/userChallenge/{userChallengeId}")
    public List<Progress> getAllProgressByUserChallenge(@PathVariable int userChallengeId) {
        if (!userChallengeRepository.existsById(userChallengeId)) throw new EntityNotFoundException();
        return progressRepository.findAllProgressByUserChallenge(userChallengeRepository.findById(userChallengeId));
   }
    
    @GetMapping(value = "/{id}")
    public Progress getProgressById(@PathVariable int id) {
        if (!progressRepository.existsById(id)) throw new EntityNotFoundException();
        return progressRepository.findProgressById(id);
   }
       
    @PostMapping
    public Progress createProgress(OAuth2Authentication auth, @RequestBody ProgressRequest progressRequest) {
        UserChallenge userChallenge;
        userChallenge = userChallengeRepository.findById(progressRequest.getUserChallengeId());

        if (userChallenge == null) throw new EntityNotFoundException();
        if (!auth.getPrincipal().toString().equals(userChallenge.getUser().getEmailAddress())) {
            throw new UnautherizedException();
        }

        Progress progress = new Progress();

        progress.setEntryDate(LocalDateTime.now());
        progress.setUserChallenge(userChallenge);

        if (progressRequest.getCurrentAmount() == 0) {
            if (userChallenge.getStartDate() != null && userChallenge.getDeadlineDate() != null) {
                long daysToComplete = ChronoUnit.DAYS.between(userChallenge.getStartDate(), userChallenge.getDeadlineDate());
                progress.setCurrentAmount(userChallenge.getAmountToComplete() / (int) daysToComplete);
            } else {
                throw new UserChallengeDateTimeExeption();
            }
        } else {
            progress.setCurrentAmount(progressRequest.getCurrentAmount());
        }
        progressRepository.save(progress);


        if (Double.parseDouble(progress.getUserChallenge().getProgressPercentage()) >= 100) {
            progress.getUserChallenge().setCompleted(true);
            userChallengeRepository.save(progress.getUserChallenge());
        }

        return progress;

    }

    @DeleteMapping(value = "/{id}")
    public void deleteProgress(@PathVariable int id) {
        if (!progressRepository.existsById(id)) throw new EntityNotFoundException();
        progressRepository.deleteById(id);
    }   
}

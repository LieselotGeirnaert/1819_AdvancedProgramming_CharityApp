package com.char1.api.controller;


import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.entity.TotalProgress;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.TotalProgressRepository;
import com.char1.api.repository.UserChallengeRepository;
import com.char1.api.request.ProgressRequest;
import com.char1.api.service.ProgressService;
import com.char1.api.service.UserChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/progress")
public class TotalProgressController {

    @Autowired
    TotalProgressRepository totalProgressRepository;
    
    @Autowired
    UserChallengeRepository userChallengeRepository;

    @Autowired
    UserChallengeService userChallengeService;

    @Autowired
    ProgressService progressService;

    @GetMapping
    public List<TotalProgress> getAllProgress() {
        return totalProgressRepository.findAll();
    }
    
    @GetMapping(value = "/userChallenge/{userChallengeId}")
    public List<TotalProgress> getAllProgressByUserChallenge(@PathVariable int userChallengeId) {
        if (!userChallengeRepository.existsById(userChallengeId)) throw new EntityNotFoundException();
        return totalProgressRepository.findAllTotalProgressByUserChallenge(userChallengeRepository.findById(userChallengeId));
   }
    
    @GetMapping(value = "/{id}")
    public TotalProgress getProgressById(@PathVariable int id) {
        if (!totalProgressRepository.existsById(id)) throw new EntityNotFoundException();
        return totalProgressRepository.findTotalProgressById(id);
   }
       
    @PostMapping
    public TotalProgress createProgress(OAuth2Authentication auth, @RequestBody ProgressRequest progressRequest) {
        UserChallenge userChallenge = userChallengeService.getUserChallengeByIdSecure(progressRequest.getUserChallengeId(), auth.getPrincipal().toString());

        TotalProgress progress = progressService.newTotalProgress(userChallenge, progressRequest.getCurrentAmount());

        if (progress.getUserChallenge().getProgressPercentage() >= 100) {
            progress.getUserChallenge().setCompleted(true);
            userChallengeRepository.save(progress.getUserChallenge());
        }
        return progress;

    }

    @DeleteMapping(value = "/{id}")
    public void deleteProgress(@PathVariable int id) {
        if (!totalProgressRepository.existsById(id)) throw new EntityNotFoundException();
        totalProgressRepository.deleteById(id);
    }   
}

package com.char1.api.controller;


import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.entity.Progress;
import com.char1.api.repository.ProgressRepository;
import com.char1.api.repository.UserChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Progress createProgress(@RequestBody Progress progress) {
        return progressRepository.save(progress);
    }
    
    @DeleteMapping(value = "/{id}")
    public void deleteProgress(@PathVariable int id) {
        if (!progressRepository.existsById(id)) throw new EntityNotFoundException();
        progressRepository.deleteById(id);
    }   
}

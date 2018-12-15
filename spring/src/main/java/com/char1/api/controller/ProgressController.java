package com.char1.api.controller;


import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.entity.Progress;
import com.char1.api.repository.ProgressRepository;
import com.char1.api.repository.UserChallengeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    
    @GetMapping(value = "/{userChallengeId}/userchallenge")
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
        return;
    }   
}

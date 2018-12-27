package com.char1.api.controller;

import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.entity.Challenge;
import com.char1.api.repository.CategoryRepository;
import com.char1.api.repository.ChallengeRepository;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/challenge")
public class ChallengeController {

    @Autowired
    ChallengeRepository challengeRepository;
    
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value = "/{idChallenge}")
    public Challenge getChallenge(@PathVariable int idChallenge) {
        return challengeRepository.findById(idChallenge);
    }

    @GetMapping
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }
    
    @GetMapping(value = "/{id}/category")
    public List<Challenge> getAllChallengesByCategory(@PathVariable int id) {
        if (!categoryRepository.existsById(id)) throw new EntityNotFoundException("category");
        return challengeRepository.findByCategory(categoryRepository.findById(id));
    }

    @PostMapping
    public Challenge newChallenge(@RequestBody Challenge challenge) {
        return challengeRepository.save(challenge);
    }

}

package com.char1.api.controller;

import com.char1.api.entity.Challenge;
import com.char1.api.repository.ChallengeRepository;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChallengeController {

    @Autowired
    ChallengeRepository challengeRepository;

    @GetMapping(value = "/challenge/{idChallenge}")
    public Challenge getChallenge(@PathVariable String idChallenge) {
        return challengeRepository.findById(NumberUtils.toInt(idChallenge));
    }

    @GetMapping(value = "/challenge")
    public List<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

}

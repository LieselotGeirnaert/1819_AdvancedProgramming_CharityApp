/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.char1.api.controller;

import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.UserChallengeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Robin
 */
@RestController
public class UserChallengeController {
    @Autowired
    UserChallengeRepository userChallengeRepository;
    
    @GetMapping(value = "/userchallenge/{userChallengeId}")
    public UserChallenge getUserChallengeById(@PathVariable String userChallengeId) {
        return userChallengeRepository.findById(Integer.parseInt(userChallengeId));
    }

    @GetMapping(value = "/userchallenge")
    public List<UserChallenge> getAllUserChallenges() {
        return userChallengeRepository.findAll();
    }
}

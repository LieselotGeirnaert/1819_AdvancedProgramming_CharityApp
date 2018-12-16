/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.char1.api.controller;

import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.UserChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Robin
 */
@RestController
@RequestMapping(value = "/userchallenge")
public class UserChallengeController {

    @Autowired
    UserChallengeRepository userChallengeRepository;
    
    @GetMapping(value = "/{id}")
    public UserChallenge getUserChallengeById(@PathVariable int id) {
        if (!userChallengeRepository.existsById(id)) throw new EntityNotFoundException();
        return userChallengeRepository.findById(id);
    }

    @GetMapping
    public List<UserChallenge> getAllUserChallenges() {
        return userChallengeRepository.findAll();
    }
    
    @PostMapping
    public UserChallenge createUserChallenge(@RequestBody UserChallenge userChallenge) {
        return userChallengeRepository.save(userChallenge);
    }
    
    @DeleteMapping(value = "/{id}")
    public void deleteUserChallenge(@PathVariable int id) {
        if (!userChallengeRepository.existsById(id)) throw new EntityNotFoundException();
        userChallengeRepository.deleteById(id);
    }
}

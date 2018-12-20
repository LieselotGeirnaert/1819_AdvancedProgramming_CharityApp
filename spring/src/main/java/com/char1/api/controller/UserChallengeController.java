/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.char1.api.controller;

import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.entity.User;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.UserChallengeRepository;
import com.char1.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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

    @Autowired
    UserRepository userRepository;
    
    @GetMapping(value = "/{id}")
    public UserChallenge getUserChallengeById(@PathVariable int id) {
        if (!userChallengeRepository.existsById(id)) throw new EntityNotFoundException();
        return userChallengeRepository.findById(id);
    }

    @GetMapping(params = { "completed" })
    public List<UserChallenge> getAllUserChallengesWithCompletedAndUser(OAuth2Authentication auth, @RequestParam("completed") boolean completed) {
        System.out.println(auth.getPrincipal());
        return userChallengeRepository.findAllByUserAndAndCompleted(userRepository.findUserByEmailAddress(auth.getPrincipal().toString()), completed);
    }

    @GetMapping
    public List<UserChallenge> getAllUserChallengesWithUser(OAuth2Authentication auth) {
        System.out.println(auth.getPrincipal());
        return userChallengeRepository.findAllByUser(userRepository.findUserByEmailAddress(auth.getPrincipal().toString()));
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

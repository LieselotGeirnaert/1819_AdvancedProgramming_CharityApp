/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.char1.api.controller;

import com.char1.api.controller.exception.EntityNotFoundException;
import com.char1.api.controller.exception.MissingValuesException;
import com.char1.api.controller.exception.UserChallengeDateTimeExeption;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.ChallengeRepository;
import com.char1.api.repository.CharityRepository;
import com.char1.api.repository.UserChallengeRepository;
import com.char1.api.repository.UserRepository;
import com.char1.api.request.UserChallengeRequest;
import com.char1.api.service.UserChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    CharityRepository charityRepository;

    @Autowired
    UserChallengeService userChallengeService;
    
    @GetMapping(value = "/{id}")
    public UserChallenge getUserChallengeById(OAuth2Authentication auth, @PathVariable int id) {
        UserChallenge userChallenge = userChallengeService.getUserChallengeByIdSecure(id, auth.getPrincipal().toString());
        return userChallenge;
    }

    @GetMapping(params = { "completed" })
    public List<UserChallenge> getAllUserChallengesWithCompletedAndUser(OAuth2Authentication auth, @RequestParam("completed") boolean completed) {
        if (completed) {
            return userChallengeRepository.findAllByUserAndAndCompletedOrDeadlineDateBefore(userRepository.findUserByEmailAddress(auth.getPrincipal().toString()), completed, LocalDateTime.now());
        } else {
            return userChallengeRepository.findAllByUserAndCompletedAndDeadlineDateAfter(userRepository.findUserByEmailAddress(auth.getPrincipal().toString()), completed, LocalDateTime.now());
        }

    }

    @GetMapping
    public List<UserChallenge> getAllUserChallengesWithUser(OAuth2Authentication auth) {
        return userChallengeRepository.findAllByUser(userRepository.findUserByEmailAddress(auth.getPrincipal().toString()));
    }
    
    @PostMapping
    public UserChallenge createUserChallenge(OAuth2Authentication auth, @RequestBody UserChallengeRequest userChallengeRequest) {
        if (userChallengeRequest.getDeadlineDate() == null) throw new MissingValuesException("deadlineDate");
        if (userChallengeRequest.getStartDate() == null) throw new MissingValuesException("startdate");
        if (userChallengeRequest.getStartDate().isAfter(userChallengeRequest.getDeadlineDate())) throw new UserChallengeDateTimeExeption();
        if (userChallengeRequest.getAmountToCompleteDaily() == 0) throw new MissingValuesException("amountToCompleteDaily");

        UserChallenge userChallenge = new UserChallenge();
        userChallenge.setAmountToComplete(userChallengeRequest.getAmountToComplete());
        userChallenge.setAmountToDonate(userChallengeRequest.getAmountToDonate());
        userChallenge.setUser(userRepository.findUserByEmailAddress(auth.getPrincipal().toString()));
        userChallenge.setChallenge(challengeRepository.findById(userChallengeRequest.getChallengeId()));
        userChallenge.setCharity(charityRepository.findById(userChallengeRequest.getCharityId()));
        userChallenge.setCompleted(false);
        userChallenge.setCompleteToDonate(userChallengeRequest.isCompleteToDonate());
        userChallenge.setStartDate(userChallengeRequest.getStartDate());
        userChallenge.setDeadlineDate(userChallengeRequest.getDeadlineDate());
        userChallenge.setAmountToCompleteDaily(userChallengeRequest.getAmountToCompleteDaily());

        if (userChallenge.getUser() == null) throw new EntityNotFoundException("user");
        if (userChallenge.getChallenge() == null) throw new EntityNotFoundException("challenge");
        if (userChallenge.getCharity() == null) throw new EntityNotFoundException("charity");

        return userChallengeRepository.save(userChallenge);
    }
    
    @DeleteMapping(value = "/{id}")
    public void deleteUserChallenge(OAuth2Authentication auth, @PathVariable int id) {
        UserChallenge userChallenge = userChallengeService.getUserChallengeByIdSecure(id, auth.getPrincipal().toString());
        userChallengeRepository.delete(userChallenge);
    }
}

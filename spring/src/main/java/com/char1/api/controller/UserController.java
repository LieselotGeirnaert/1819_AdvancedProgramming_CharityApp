package com.char1.api.controller;

import com.char1.api.entity.Challenge;
import com.char1.api.entity.User;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.ChallengeRepository;
import com.char1.api.repository.UserChallengeRepository;
import com.char1.api.repository.UserRepository;
import edu.emory.mathcs.backport.java.util.Collections;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserChallengeRepository userChallengeRepository;

    @GetMapping(value = "/user/{idUser}")
    public User getUser(@PathVariable String idUser) {
        return userRepository.findByIdOrEmailAddress(NumberUtils.toInt(idUser), idUser);
    }

    @GetMapping(value = "/user")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(
            value = "/user/{idUser}/challenge",
            params = { "completed" },
            method = GET)
    public List<UserChallenge> getAllChallengesOfUserWithCompletion(@PathVariable String idUser, @RequestParam("completed") boolean completed) {
        return userChallengeRepository.findAllByUserAndAndCompleted(getUser(idUser), completed);
    }

    @RequestMapping(
            value = "/user/{idUser}/challenge",
            method = GET)
    public List<UserChallenge> getAllChallengesOfUser(@PathVariable String idUser) {
        return userChallengeRepository.findAllByUser(getUser(idUser));
    }

    @PostMapping("/user")
    public User newUser(@RequestBody User user) {
        return userRepository.save(user);
    }






}

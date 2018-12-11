package com.char1.api.controller;

import com.char1.api.entity.Challenge;
import com.char1.api.entity.User;
import com.char1.api.entity.UserChallenge;
import com.char1.api.repository.ChallengeRepository;
import com.char1.api.repository.UserChallengeRepository;
import com.char1.api.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/user/{idUser}/challenge")
    public List<UserChallenge> getAllChallengesOfUser(@PathVariable String idUser) {
        return userChallengeRepository.findAllByUser(getUser(idUser));
    }

    @PostMapping("/user")
    public User newUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}

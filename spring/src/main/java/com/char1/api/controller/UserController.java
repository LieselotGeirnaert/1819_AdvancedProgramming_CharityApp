package com.char1.api.controller;

import com.char1.api.controller.exception.DuplicateEntityException;
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
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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

    @GetMapping(value = "/user")
    @ResponseBody
    public User getUser(OAuth2Authentication auth) {
        return userRepository.findUserByEmailAddress(auth.getPrincipal().toString());
    }

    @PostMapping("/user")
    public User newUser(@RequestBody User user) {
        if (userRepository.existsByEmailAddress(user.getEmailAddress())) throw new DuplicateEntityException();
        return userRepository.save(user);
    }
}

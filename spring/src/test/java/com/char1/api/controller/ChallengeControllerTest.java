package com.char1.api.controller;

import com.char1.api.RestServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={RestServerApplication.class})
public class ChallengeControllerTest {

    @Autowired
    ChallengeController challengeController;

    @Test
    public void getChallenge() {
        assertNotNull(challengeController.getChallenge("1"));
    }

    @Test
    public void getChallenges() {
        assertNotNull(challengeController.getAllChallenges());
    }
}
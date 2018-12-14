/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.char1.api.controller;

import com.char1.api.RestServerApplication;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Robin
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={RestServerApplication.class})
public class UserChallengeControllerTest {
    
    @Autowired
    UserChallengeController userChallengeController;
      
    @Test
    public void getProgress() {
        assertNotNull(userChallengeController.getAllUserChallenges());
    }

    @Test
    public void getProgressByUserChallengeId() {
        assertNotNull(userChallengeController.getUserChallengeById("1"));
    }   
}

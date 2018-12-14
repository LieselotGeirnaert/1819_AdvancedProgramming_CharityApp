/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.char1.api.controller;

import com.char1.api.RestServerApplication;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class ProgressControllerTest {
    
    @Autowired
    ProgressController progressController;
    
    @Autowired
    UserController userController;
    
    @Test
    public void getProgress() {
        assertNotNull(progressController.getAllProgress());
    }

    @Test
    public void getProgressByUserChallengeId() {
        assertNotNull(progressController.getAllProgressByUserChallenge("1"));
    }
    
    

}

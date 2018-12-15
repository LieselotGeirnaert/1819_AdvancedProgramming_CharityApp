/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.char1.api.functionalTest.controller;

import com.char1.api.RestServerApplication;
import com.char1.api.controller.ProgressController;
import com.char1.api.controller.UserController;
import com.char1.api.entity.Challenge;
import com.char1.api.entity.Progress;
import com.char1.api.entity.User;
import com.char1.api.entity.UserChallenge;
import com.char1.api.functionalTest.FunctionalTest;
import com.char1.api.repository.ProgressRepository;
import com.char1.api.repository.UserChallengeRepository;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
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
public class ProgressTest extends FunctionalTest{
    
    private Progress dummyProgress;

    @Autowired
    private UserChallengeRepository userChallengeRepository;
    
    @Autowired 
    private ProgressRepository progressRepository;
    
    public ProgressTest() {
        // Create Dummy data
        dummyProgress = new Progress();
        UserChallenge dummyUserChallenge = new UserChallenge();
        Challenge dummyChallenge = new Challenge();
        User dummyUser = new User();
        
        dummyUser.setEmailAddress("dummy.test@tester.com");
        dummyChallenge.setTitle("Dummy Challenge Title");
        dummyUserChallenge.setUser(dummyUser);
        dummyUserChallenge.setChallenge(dummyChallenge);
        dummyUserChallenge.setCompleted(false);
        dummyUserChallenge.setCompleteToDonate(false);
        dummyProgress.setUserChallenge(dummyUserChallenge);
    }
    
    @Before
    public void CreateDummyData() {
        dummyProgress = progressRepository.save(dummyProgress);
    }

    @After
    public void RemoveDummyData() {
        progressRepository.deleteAll();
    }
    
    @Test
    public void createNewProgress() {
        Progress newProgress = new Progress();
        UserChallenge newUserChallenge = new UserChallenge();
        Challenge dummyChallenge = new Challenge();
        User dummyUser = new User();
        
        dummyUser.setEmailAddress("dummy.test@tester.com");
        dummyChallenge.setTitle("Dummy Challenge Title");
        newUserChallenge.setUser(dummyUser);
        newUserChallenge.setChallenge(dummyChallenge);
        newUserChallenge.setCompleted(false);
        newUserChallenge.setCompleteToDonate(false);
        newProgress.setUserChallenge(newUserChallenge);
       
        given()
                .spec(super.requestSpecification)
                .body(newProgress)
                .when().post("/progress")
                .then().statusCode(200);
    }
    
    @Test
    public void getAllProgress() {
        createNewProgress();

        given()
                .spec(super.requestSpecification)
                .when().get("/progress")
                .then().statusCode(200).and().body("size()", equalTo(2));
    }

    @Test
    public void getProgressByUserChallengeId() {
        given()
                .spec(super.requestSpecification)
                .when().get("/progress/" + dummyProgress.getUserChallenge().getId() + "/userchallenge")
                .then().statusCode(200);
    }
    
    @Test
    public void getProgressById() {
        given()
                .spec(super.requestSpecification)
                .when().get("/progress/" + dummyProgress.getId())
                .then().statusCode(200);
    }
    
        @Test
    public void getProgressWithInvalidId() {
        given()
                .spec(super.requestSpecification)
                .when().get("/progress/1a")
                .then().statusCode(400);
    }

    @Test
    public void getProgressWithZeroId() {
        given()
                .spec(super.requestSpecification)
                .when().get("/progress/0")
                .then().statusCode(404);
    }

    @Test
    public void deleteProgressWithInvalidId() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/progress/1a")
                .then().statusCode(400);
    }

    @Test
    public void deleteProgressWithZeroId() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/progress/0")
                .then().statusCode(404);
    }

    @Test
    public void deleteProgress() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/progress/" + dummyProgress.getId())
                .then().statusCode(200);
    }
 
}

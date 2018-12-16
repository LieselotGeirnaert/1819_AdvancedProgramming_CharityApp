/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.char1.api.functionalTest.controller;

import com.char1.api.entity.Challenge;
import com.char1.api.entity.User;
import com.char1.api.entity.UserChallenge;
import com.char1.api.functionalTest.FunctionalTest;
import com.char1.api.repository.UserChallengeRepository;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Robin
 */

public class UserChallengeTest extends FunctionalTest {
    
    private UserChallenge dummyUserChallenge;
    
    @Autowired
    private UserChallengeRepository userChallengeRepository;
    
    public UserChallengeTest() {
        // Create Dummy data
        dummyUserChallenge = new UserChallenge();
        Challenge dummyChallenge = new Challenge();
        User dummyUser = new User();
        
        dummyUser.setEmailAddress("dummy.test@tester.com");
        dummyChallenge.setTitle("Dummy Challenge Title");
        dummyUserChallenge.setUser(dummyUser);
        dummyUserChallenge.setChallenge(dummyChallenge);
        dummyUserChallenge.setCompleted(false);
        dummyUserChallenge.setCompleteToDonate(false);
    }
    
    @Before
    public void CreateDummyData() {
        dummyUserChallenge = userChallengeRepository.save(dummyUserChallenge);
    }

    @After
    public void RemoveDummyData() {
        userChallengeRepository.deleteAll();
    }
    
    @Test
    public void createNewUserChallenge() {
        UserChallenge newUserChallenge = new UserChallenge();
        
        Challenge dummyChallenge = new Challenge();
        User dummyUser = new User();
        
        dummyUser.setEmailAddress("dummy.test@tester.com");
        dummyChallenge.setTitle("Dummy Challenge Title");
        newUserChallenge.setUser(dummyUser);
        newUserChallenge.setChallenge(dummyChallenge);
        newUserChallenge.setCompleted(false);
        newUserChallenge.setCompleteToDonate(false);
       
        given()
                .spec(super.requestSpecification)
                .body(newUserChallenge)
                .when().post("/userchallenge")
                .then().statusCode(200);
    }
    
    @Test
    public void getAllUserChallenges() {
        createNewUserChallenge();

        given()
                .spec(super.requestSpecification)
                .when().get("/userchallenge")
                .then().statusCode(200).and().body("size()", equalTo(2));
    }

    @Test
    public void getUserChallengeById() {
        given()
                .spec(super.requestSpecification)
                .when().get("/userchallenge/" + dummyUserChallenge.getId())
                .then().statusCode(200);
    }
    
        @Test
    public void getUserChallengeWithInvalidId() {
        given()
                .spec(super.requestSpecification)
                .when().get("/userchallenge/1a")
                .then().statusCode(400);
    }

    @Test
    public void getUserChallengeWithZeroId() {
        given()
                .spec(super.requestSpecification)
                .when().get("/userchallenge/0")
                .then().statusCode(404);
    }

    @Test
    public void deleteUserChallengeWithInvalidId() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/userchallenge/1a")
                .then().statusCode(400);
    }

    @Test
    public void deleteUserChallengeWithZeroId() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/userchallenge/0")
                .then().statusCode(404);
    }

    @Test
    public void deleteUserChallenge() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/userchallenge/" + dummyUserChallenge.getId())
                .then().statusCode(200);
    }
 
}

package com.char1.api.functionalTest.controller;


import com.char1.api.entity.*;
import com.char1.api.functionalTest.FunctionalTest;
import com.char1.api.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

public class ChallengeTest extends FunctionalTest {

    private Challenge dummyChallenge;
    private Category dummyCategory;

    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ChallengeTest() {     
      
        dummyCategory = new Category();
        dummyCategory.setName("Sports");
        Set<Category> dummyCategorySet = new HashSet<>();
        dummyCategorySet.add(dummyCategory);

        dummyChallenge = new Challenge();
        dummyChallenge.setCategory(dummyCategorySet);
        dummyChallenge.setDescription("hotdogs eten");
        dummyChallenge.setLinkToLogo("http://something.com");
        dummyChallenge.setTitle("HOTDOGS");
        dummyChallenge.setUnitToMeasure("hotdogs");
        
    }

    @Before
    public void createDummyData() {
        challengeRepository.saveAndFlush(dummyChallenge);
    }

    @After
    public void RemoveDummyData() {
        challengeRepository.deleteAll();

    }

    @Test
    public void createChallenge() {
        Challenge newChallenge = new Challenge();
        
        Set<Category> dummyCategorySet = new HashSet<>();
        dummyCategorySet.add(dummyCategory);

        newChallenge = new Challenge();
        newChallenge.setCategory(dummyCategorySet);
        newChallenge.setDescription("hotdogs eten");
        newChallenge.setLinkToLogo("http://something.com");
        newChallenge.setTitle("HOTDOGS");
        newChallenge.setUnitToMeasure("hotdogs");

        given()
                .spec(super.requestSpecification)
                .body(newChallenge)
                .when().post("/userchallenge")
                .then().statusCode(200);
    }

    @Test
    public void getAllChallenges() {
        createChallenge();
        given()
                .spec(super.requestSpecification)
                .when().get("/challenge")
                .then().statusCode(200).and().body("size()", equalTo(2));
    }

    @Test
    public void getChallenge() {
        given()
                .spec(super.requestSpecification)
                .when().get("/challenge/" + dummyChallenge.getId())
                .then().statusCode(200);
    }

    @Test
    public void getChallengeWithInvalidId() {
        given()
                .spec(super.requestSpecification)
                .when().get("/challenge/1aa")
                .then().statusCode(400);
    }

    @Test
    public void getChallengeWithZeroId() {
        given()
                .spec(super.requestSpecification)
                .when().get("/challenge/0")
                .then().statusCode(404);
    }

    @Test
    public void deleteChallengeWithInvalidId() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/challenge/1a")
                .then().statusCode(400);
    }

    @Test
    public void deleteChallengeWithZeroId() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/challenge/0")
                .then().statusCode(404);
    }

    @Test
    public void deleteChallenge() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/challenge/" + dummyChallenge.getId())
                .then().statusCode(200);
    }
}

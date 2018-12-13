package com.char1.api.functionalTest.controller;

import com.char1.api.functionalTest.FunctionalTest;
import org.junit.Test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

public class CharityTest extends FunctionalTest {

    @Test
    public void createCharity() {
        given().
        when().
            get("/charity").
        then().
            statusCode(200);
    }

}

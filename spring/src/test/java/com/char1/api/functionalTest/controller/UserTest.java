package com.char1.api.functionalTest.controller;

import com.char1.api.entity.BankAccount;
import com.char1.api.entity.User;
import com.char1.api.functionalTest.FunctionalTest;
import com.char1.api.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserTest extends FunctionalTest {

    private User dummyUser;

    @Autowired
    private UserRepository userRepository;

    public UserTest() {
        // Create Dummy data
        BankAccount userBankAccount = new BankAccount();
        userBankAccount.setBankAccount("BE16720332171231");
        dummyUser = new User();
        dummyUser.setEmailAddress("dummy@example.org");
        dummyUser.setFirstName("test");
        dummyUser.setLastName("tester");
        dummyUser.setPassword("password");
        dummyUser.setBankAccount(userBankAccount);
    }

    @Before
    public void createDummyData() {
        dummyUser = userRepository.save(dummyUser);
    }

    @After
    public void RemoveDummyData() {
        userRepository.deleteAll();
    }

    @Test
    public void createUser() {
        BankAccount testBankAccount = new BankAccount();
        testBankAccount.setBankAccount("BE84166940171166");
        User user = new User();
        user.setEmailAddress("dummy2@example.org");
        user.setFirstName("test2");
        user.setLastName("tester2");
        user.setPassword("password");
        user.setBankAccount(testBankAccount);

        given()
                .spec(super.requestSpecification)
                .body(user)
                .when().post("/user")
                .then().statusCode(200);
    }

    @Test
    public void createDuplicateUser() {
        given()
                .spec(super.requestSpecification)
                .body(dummyUser)
                .when().post("/user")
                .then().statusCode(400);
    }

    @Test
    public void getUser() {
        given()
                .spec(super.requestSpecification)
                .when().get("/user")
                .then().statusCode(200)
                .body("id", equalTo(authenticatedUser.getId()));
    }
}

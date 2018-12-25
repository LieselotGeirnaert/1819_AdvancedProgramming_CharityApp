package com.char1.api.functionalTest.controller;

import com.char1.api.entity.BankAccount;
import com.char1.api.entity.User;
import com.char1.api.functionalTest.FunctionalTest;
import com.char1.api.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

public class UserTest extends FunctionalTest {

    private User dummyUser;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserTest() {
        // Create Dummy data
        BankAccount userBankAccount = new BankAccount();
        userBankAccount.setBankAccount("BE16720332171231");
        dummyUser = new User();
        dummyUser.setEmailAddress("test@user.be");
        dummyUser.setFirstName("test");
        dummyUser.setLastName("tester");
        dummyUser.setBankAccount(userBankAccount);
        dummyUser.setPassword(passwordEncoder().encode("password"));
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
        user.setEmailAddress("test2@user.be");
        user.setFirstName("test2");
        user.setLastName("tester2");
        user.setBankAccount(testBankAccount);
        user.setPassword(passwordEncoder().encode("password"));

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
                .then().statusCode(200);
    }
}

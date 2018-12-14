package com.char1.api.functionalTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecBuilder;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionalTest {

    @Value("${server.host}")
    private String host;

    @Value("${server.port}")
    private int port;

    @Value("${server.base}")
    private String base;

    @Autowired
    private WebApplicationContext webAppContext;

    protected MockMvcRequestSpecification requestSpecification;

    @Before
    public void setUp() {
        RestAssured.baseURI = host;
        RestAssured.port = port;
        RestAssured.basePath = base;
        RestAssuredMockMvc.webAppContextSetup(webAppContext);
        requestSpecification = new MockMvcRequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void contextLoads() {

    }

}

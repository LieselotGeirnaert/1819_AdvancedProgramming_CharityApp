package com.char1.api.functionalTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecBuilder;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.common.util.JacksonJsonParser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.RestAssured.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

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

    protected RequestSpecification requestSpecification;

    protected String token;



    public void obtainAccessToken(String username, String password) throws JSONException {
        Response response = given().auth().preemptive().basic("char1Client", "f2a1ed52710d4533bde25be6da03b6e3")
                .formParam("grant_type", "password")
                .formParam("username", username)
                .formParam("password", password)
                .formParam("scope", "read")
                .when().post("/oauth/token");
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        token = jsonObject.get("access_token").toString();
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = host;
        RestAssured.port = port;
        RestAssured.basePath = base;

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
        try {
            obtainAccessToken("test@tester.be", "hash");
        } catch (Exception e) {
            System.out.println("couldnt get access token: " + e.getMessage());

        }
    }

    @Test
    public void contextLoads() {

    }

}

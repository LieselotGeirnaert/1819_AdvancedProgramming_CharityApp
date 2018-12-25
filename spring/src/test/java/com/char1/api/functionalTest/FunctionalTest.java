package com.char1.api.functionalTest;


import com.char1.api.entity.User;
import com.char1.api.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecBuilder;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FunctionalTest {

    @Value("${server.host}")
    private String host;
    @Value("${server.port}")
    private int port;
    @Value("${server.base}")
    private String base;

    protected User testUser;

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    protected UserRepository userRepository;

    protected MockMvcRequestSpecification requestSpecification;

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Before
    public void setUp() {
        setUpRestAssured();

        String emailAddress = "peter@example.org";
        String password = "test";

        createTestUser(emailAddress, password);

        String oAuth2AccessToken = obtainAccessToken(emailAddress, password);
        requestSpecification = buildMockMvcRequestSpecification(oAuth2AccessToken);
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    private void setUpRestAssured() {
        RestAssured.baseURI = host;
        RestAssured.port = port;
        RestAssured.basePath = base;
        RestAssuredMockMvc.webAppContextSetup(webAppContext);
    }

    private void createTestUser(String emailAddress, String password) {
        testUser = new User();
        testUser.setEmailAddress(emailAddress);
        testUser.setPassword(password);
        testUser.setFirstName("Peter");
        testUser.setLastName("Baelish");
        userRepository.saveAndFlush(testUser);
    }

    private String obtainAccessToken(String username, String password) {

        return given().auth().with(httpBasic("char1Client", "f2a1ed52710d4533bde25be6da03b6e3"))
                .formParam("grant_type", "password")
                .formParam("username", username)
                .formParam("password", password)
                .formParam("scope", "read")
                .when().post("/oauth/token")
                .getBody().jsonPath().get("access_token");
    }

    private MockMvcRequestSpecification buildMockMvcRequestSpecification(String oAuth2AccessToken) {
        return new MockMvcRequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + oAuth2AccessToken)
                .build();
    }

    @Test
    public void contextLoads() {

    }

}

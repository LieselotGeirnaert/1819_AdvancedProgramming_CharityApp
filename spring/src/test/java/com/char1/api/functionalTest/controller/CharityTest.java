package com.char1.api.functionalTest.controller;

import com.char1.api.entity.BankAccount;
import com.char1.api.entity.Charity;
import com.char1.api.functionalTest.FunctionalTest;
import com.char1.api.repository.CharityRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

public class CharityTest extends FunctionalTest {

    private Charity dummyCharity;

    @Autowired
    private CharityRepository charityRepository;

    public CharityTest() {
        // Create Dummy data
        BankAccount oxfamBankAccount = new BankAccount();
        oxfamBankAccount.setBankAccount("BE16720332171231");
        dummyCharity = new Charity();
        dummyCharity.setName("Oxfam VZW");
        dummyCharity.setDescription("Oxfam International is een organisatie die strijdt tegen honger in de wereld en die ontwikkeling bevordert");
        dummyCharity.setLinkToLogo("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");
        dummyCharity.setBankAccount(oxfamBankAccount);
    }

    @Before
    public void createDummyData() {
        dummyCharity = charityRepository.save(dummyCharity);
    }

    @After
    public void RemoveDummyData() {
        charityRepository.deleteAll();
    }

    @Test
    public void createCharity() {
        BankAccount rodeKruisBankAccount = new BankAccount();
        rodeKruisBankAccount.setBankAccount("BE84166940171166");
        Charity redCrossCharity = new Charity();
        redCrossCharity.setName("Rode Kruis VZW");
        redCrossCharity.setDescription("Het Rode Kruis is een wereldwijde hulpverleningsorganisatie waarvan de werking in zeven basisbeginselen is gegoten: menslievendheid, neutraliteit, onpartijdigheid, onafhankelijkheid, vrijwilligheid, eenheid en algemeenheid.");
        redCrossCharity.setLinkToLogo("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sK9ryrNh9A1ZsPoOi4l9wCT8xhhhhhhUDBAB7NpBRdKIBPc/MQYYYYTaeIBlf35tuiReMqht0SLhlUNOiRcMqhp0SLhlUNOiRcMqhh0SLhlUGs+QPZ0IMiUpSlKQvhmJ7O5/DP/aAAwDAQACAAMAAAAQ88888888888888888888888888888888888888NNNMa88888888888DAABW88888888888DAABW88888888888DAABW8888888sDDDAAAByDDD88888AAAAAAAAAAB88888AAAAAAAAABB88888CCCBAAACCCB8888DyyyBAABzyyE88888888DAABW88888888888DAABW88888888888pNNNT8888888888888888888888888888888888888/8QAJBEAAQEHBQEBAQAAAAAAAAAAAQARIDFRYZGxITBxofBBUOH/2gAIAQMBAT8Q/GJ2PVioLKgsqCyN9oItuRuWA54U9yNywHPCnuRuWA54U9gC+gDewqRuVSNyqRuUzfpFwAJbqiqRuVSNyhTFoMXfVUbfY4d9VRt9jh0gGvjQmJpiaYmgQYOMTTE0xNAjfeHddJiqbqpuqm6KiX1gOCiBf2VTdVN0RGEtmNywHPCnuRuWA54U9xiFLTgKg7VB2qDtQGSWfxv/xAAhEQABBAICAgMAAAAAAAAAAAAAAREgYTChMbEhUEFRkf/aAAgBAgEBPxD0zTisrKBjyOiGrk6IauTohq4EuN5eXj2hYCF5fJI2se/Hax78doZRlGWLKMoyif3iqORUVFQl4PqHCvgqKirD0Q1cnRDVyPZJWVlYsgi+m//EACYQAAIABQMFAAMBAAAAAAAAAAABESEwUfBAYcExQVBx0SBwsYH/2gAIAQEAAT8Q/RjJKLcEiOv6CCwgsILCCwgsILBhJoajp1Ft7gQVAs2bNmzZtFKz1cDcxT9HpnS/dWLSul+asWldL81YtK6X5qxaV0vzVi1+5q676RSmPIWiUaIlH8xBBBBBBSJsfUmyg4ZtLxm3co7iT+MIIIISe8DPiKVCgj2awulDZ105/HX+Af8AEX+Af8Rf4B/xF/gH/EX+Af8AEXUe+n/4i+j2P7xPnGjOuDGuDGuDGuDGuDOuCFGCPbioIjIfdebQlSjRw6GdcGNcGNcGNcDtGFhHVHRPf6lRURyYmT3J7k9ye5Oiu2Ys49tyZPcnuT3J3ROy1W5qxaV0vzVi0rpfmrFr/wA1Ytc+aUKlGd9Oh0Abgsno6Pcx2M34M34M34M34M34M34Ex2o6Jt0o+xKCgL9Ff//Z");
        redCrossCharity.setBankAccount(rodeKruisBankAccount);

        given()
                .spec(super.requestSpecification)
                .body(redCrossCharity)
                .when().post("/charity")
                .then().statusCode(200);
    }

    @Test
    public void createDuplicateCharity() {
        given()
                .spec(super.requestSpecification)
                .body(dummyCharity)
                .when().post("/charity")
                .then().statusCode(400);
    }

    @Test
    public void getAllCharities() {
        createCharity();

        given()
                .spec(super.requestSpecification)
                .when().get("/charity")
                .then().statusCode(200).and().body("size()", equalTo(2));
    }

    @Test
    public void getCharity() {
        given()
                .spec(super.requestSpecification)
                .when().get("/charity/" + dummyCharity.getId())
                .then().statusCode(200);
    }

    @Test
    public void getCharityWithInvalidId() {
        given()
                .spec(super.requestSpecification)
                .when().get("/charity/1a")
                .then().statusCode(400);
    }

    @Test
    public void getCharityWithZeroId() {
        given()
                .spec(super.requestSpecification)
                .when().get("/charity/0")
                .then().statusCode(404);
    }

    @Test
    public void deleteCharityWithInvalidId() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/charity/1a")
                .then().statusCode(400);
    }

    @Test
    public void deleteCharityWithZeroId() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/charity/0")
                .then().statusCode(404);
    }

    @Test
    public void deleteCharity() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/charity/" + dummyCharity.getId())
                .then().statusCode(200);
    }

}

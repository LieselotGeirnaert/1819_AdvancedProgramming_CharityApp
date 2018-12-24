package com.char1.api.functionalTest.controller;

import com.char1.api.entity.Category;
import com.char1.api.functionalTest.FunctionalTest;
import com.char1.api.repository.CategoryRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

public class CategoryTest extends FunctionalTest {

    private Category dummyCategory;

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryTest() {
        // Create Dummy data
        dummyCategory = new Category();
        dummyCategory.setName("Sports");
    }

    @Before
    public void createDummyData() {
        dummyCategory = categoryRepository.save(dummyCategory);
    }

    @After
    public void RemoveDummyData() {
        categoryRepository.deleteAll();
    }

    @Test
    public void createCategory() {
        Category FunCategory = new Category();
        FunCategory.setName("Fun");

        given()
                .spec(super.requestSpecification)
                .body(FunCategory)
                .when().post("/category")
                .then().statusCode(200);
    }

    @Test
    public void createDuplicateCategory() {
        given()
                .spec(super.requestSpecification)
                .body(dummyCategory)
                .when().post("/category")
                .then().statusCode(400);
    }

    @Test
    public void getAllCategories() {
        createCategory();

        given()
                .spec(super.requestSpecification)
                .when().get("/category")
                .then().statusCode(200).and().body("size()", equalTo(2));
    }

    @Test
    public void getCategory() {
        given()
                .spec(super.requestSpecification)
                .when().get("/category/" + dummyCategory.getId())
                .then().statusCode(200);
    }

    @Test
    public void getCategoryWithInvalidId() {
        given()
                .spec(super.requestSpecification)
                .when().get("/category/1a")
                .then().statusCode(400);
    }

    @Test
    public void getCategoryWithZeroId() {
        given()
                .spec(super.requestSpecification)
                .when().get("/category/0")
                .then().statusCode(404);
    }

    @Test
    public void deleteCategoryWithInvalidId() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/category/1a")
                .then().statusCode(400);
    }

    @Test
    public void deleteCategoryWithZeroId() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/category/0")
                .then().statusCode(404);
    }

    @Test
    public void deleteCategory() {
        given()
                .spec(super.requestSpecification)
                .when().delete("/category/" + dummyCategory.getId())
                .then().statusCode(200);
    }

}

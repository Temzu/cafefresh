package com.temzu.cafefresh.controllers;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class CategoryControllerTest {

  @Autowired
  private ServletWebServerApplicationContext webServerAppCtxt;

  @BeforeEach
  public void setUp() {
    RestAssured.port = webServerAppCtxt.getWebServer().getPort();
  }

  @Test
  @Transactional(isolation = Isolation.SERIALIZABLE)
  void findActivePage() {
    String response = given()
        .get("/cafefresh/api/v1/categories")
        .then()
        .extract()
        .response()
        .asString();

//    List<ProductDto> orderDtos = pageResponse.jsonPath().getList("content", ProductDto.class);

    System.out.println(response);
  }

  @Test
  void findPageAll() {


  }

  @Test
  void findAll() {

  }

  @Test
  void findPageByCategory() {
    }

  @Test
  void createCategory() {
    }

  @Test
  void update() {
    }

  @Test
  void deleteById() {
    }

  @Test
  void uploadCategoryImage() {
    }
}
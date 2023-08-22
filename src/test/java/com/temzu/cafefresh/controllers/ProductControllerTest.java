package com.temzu.cafefresh.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import com.temzu.cafefresh.dtos.OrderDto;
import com.temzu.cafefresh.dtos.ProductDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class ProductControllerTest {


  @Autowired
  private ServletWebServerApplicationContext webServerAppCtxt;

  @BeforeEach
  public void setUp() {
    RestAssured.port = webServerAppCtxt.getWebServer().getPort();
  }

  @Test
  void findPage() {
    Response pageResponse =
        given()
            .when()
            .get("/cafefresh/api/v1/products")
            .then()
            .extract()
            .response();

    List<ProductDto> productDtos = pageResponse.jsonPath().getList("content", ProductDto.class);

    assertNotNull(productDtos);
    assertFalse(productDtos.isEmpty());

    get("/cafefresh/api/v1/products")
        .then()
        .assertThat()
        .body("numberOfElements", equalTo(10));
  }

  @Test
  void findPageByCategory() {

  }

  @Test
  void findCategoryPage() {

  }

  @Test
  void findAll() {
  }

  @Test
  void findById() {
  }

  @Test
  void save() {
  }

  @Test
  void update() {
  }

  @Test
  void deleteById() {
  }

}
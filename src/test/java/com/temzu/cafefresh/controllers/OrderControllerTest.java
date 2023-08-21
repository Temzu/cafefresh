package com.temzu.cafefresh.controllers;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import com.temzu.cafefresh.dtos.OrderDto;
import com.temzu.cafefresh.entities.Role;
import com.temzu.cafefresh.entities.UserInfo;
import com.temzu.cafefresh.services.RedisService;
import com.temzu.cafefresh.services.TokenService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class OrderControllerTest {

  @Autowired
  private ServletWebServerApplicationContext webServerAppCtxt;

  @MockBean
  private TokenService tokenService;

  @MockBean
  private RedisService redisService;

  @BeforeEach
  public void setUp() {
    RestAssured.port = webServerAppCtxt.getWebServer().getPort();
  }

  @CsvSource({
    "Bearer Token_1,  user, 1",
    "Bearer Token_2,  admin, 2",
    "Bearer Token_3', manager, 3"
  })
  @ParameterizedTest
  void findPageByCurrentUser(String token, String login, Long userId) {
    List<String> roles = new ArrayList<>();
    roles.add(login.toUpperCase(Locale.ROOT));

    Mockito.when(tokenService.parseToken(token))
        .thenReturn(new UserInfo(userId, login, login + "@gmail.com", roles));

    UserInfo userInfo = tokenService.parseToken(token);
    System.out.println(userInfo);

    Mockito.when(redisService.exists(token + "_token")).thenReturn(true);
    Mockito.when(redisService.get(token)).thenReturn(token);

    Response pageResponse =
        given()
            .header("AUTHORIZATION", token)
            .when()
            .get("/cafefresh/api/v1/orders")
            .then()
            .extract()
            .response();

    List<OrderDto> orderDtos = pageResponse.jsonPath().getList("content", OrderDto.class);
    System.out.println(orderDtos);

    assertNotNull(orderDtos);
    assertFalse(orderDtos.isEmpty());
  }
}

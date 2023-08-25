package com.temzu.cafefresh.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.temzu.cafefresh.dtos.AuthRequestDto;
import com.temzu.cafefresh.dtos.SignUpRequestDto;
import com.temzu.cafefresh.exceptions.ResourceAlreadyExistsException;
import com.temzu.cafefresh.exceptions.ResourceNotFoundException;
import com.temzu.cafefresh.services.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AuthServiceImplTest {

  @Autowired
  private AuthService authService;

  @Order(1)
  @CsvSource(value = {"ivan1", "petr2", "andrey3"})
  @ParameterizedTest
  void signUp_CORRECT(String login) {
    SignUpRequestDto request = new SignUpRequestDto(login, "123");

    assertDoesNotThrow(() -> authService.signUp(request));
  }

  @Order(2)
  @CsvSource(value = {"ivan", "petr", "andrey", "ivan1", "petr2", "andrey3"})
  @ParameterizedTest
  void signUp_WRONG(String login) {
    SignUpRequestDto request = new SignUpRequestDto(login, "123");

    assertThrows(ResourceAlreadyExistsException.class, () -> authService.signUp(request));
  }

  @Order(3)
  @CsvSource(value = {"ivan", "petr", "andrey", "ivan1", "petr2", "andrey3"})
  @ParameterizedTest
  void login_CORRECT(String login) {
    AuthRequestDto request = new AuthRequestDto(login, "123");

    assertDoesNotThrow(() -> authService.login(request));
  }

  @Order(4)
  @CsvSource(value = {"kasdjf", "asdf", "123sdfasdf"})
  @ParameterizedTest
  void login_when_login_does_not_exists(String login) {
    AuthRequestDto request = new AuthRequestDto(login, "123");

    assertThrows(ResourceNotFoundException.class, () -> authService.login(request));
  }

  @Order(5)
  @CsvSource(value = {"ivan", "petr", "andrey", "ivan1", "petr2", "andrey3"})
  @ParameterizedTest
  void login_when_wrong_password(String login) {
    AuthRequestDto request = new AuthRequestDto(login, "321");

//    assertThrows(UserWrongPasswordException.class, () -> authService.login(request));
  }
}
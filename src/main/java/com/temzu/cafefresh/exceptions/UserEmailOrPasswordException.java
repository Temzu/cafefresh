package com.temzu.cafefresh.exceptions;

public class UserEmailOrPasswordException extends RuntimeException {

  private static final String WRONG_EMAIL_PASSWORD = "Wrong email or password entered";

  public UserEmailOrPasswordException() {
    super(WRONG_EMAIL_PASSWORD);
  }
}

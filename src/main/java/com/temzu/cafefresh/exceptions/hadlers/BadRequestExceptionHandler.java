package com.temzu.cafefresh.exceptions.hadlers;

import com.temzu.cafefresh.exceptions.UserEmailOrPasswordException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadRequestExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> messages =
        ex.getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());
    return new ResponseEntity<>(new CustomError(messages), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UserEmailOrPasswordException.class)
  public ResponseEntity<?> catchUserLoginOrPasswordException(UserEmailOrPasswordException ex) {
    return new ResponseEntity<>(new CustomError(ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

}

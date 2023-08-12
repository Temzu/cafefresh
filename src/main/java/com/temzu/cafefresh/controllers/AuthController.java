package com.temzu.cafefresh.controllers;

import com.temzu.cafefresh.dtos.AuthRequestDto;
import com.temzu.cafefresh.dtos.AuthResponseDto;
import com.temzu.cafefresh.dtos.SignUpRequestDto;
import com.temzu.cafefresh.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;


  @PostMapping("/signup")
  public AuthResponseDto signUp(@Valid @RequestBody SignUpRequestDto signUpRequest) {
    return authService.signUp(signUpRequest);
  }

  @PostMapping("/login")
  public AuthResponseDto login(@Valid @RequestBody AuthRequestDto request) {
    return authService.login(request);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/logout")
  public void logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
    authService.logout(token);
  }
}

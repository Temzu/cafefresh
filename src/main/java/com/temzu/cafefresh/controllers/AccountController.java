package com.temzu.cafefresh.controllers;

import com.temzu.cafefresh.dtos.AccountChangePassDto;
import com.temzu.cafefresh.dtos.AuthResponseDto;
import com.temzu.cafefresh.dtos.UserDto;
import com.temzu.cafefresh.services.UserService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@PreAuthorize("isAuthenticated()")
public class AccountController {

  private final UserService userService;

  @GetMapping("/current")
  public UserDto findCurrentAccount(Principal principal) {
    return userService.findUserByLogin(principal.getName());
  }

  @PostMapping("/current")
  public UserDto changeCurrentAccountInfo(Principal principal, UserDto userDto) {
    UserDto curUser = userService.findUserByLogin(principal.getName());
    curUser.setEmail(userDto.getEmail());
    curUser.setLogin(userDto.getLogin());
    return userService.update(curUser);
  }

  @PostMapping("/change_pass")
  public AuthResponseDto changeAccountPass(
      Principal principal,
      @RequestBody AccountChangePassDto passDto,
      @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) {
    return userService.updatePass(passDto.getPassword(), principal.getName(), token);
  }

}

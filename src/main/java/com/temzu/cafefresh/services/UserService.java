package com.temzu.cafefresh.services;

import com.temzu.cafefresh.dtos.AuthResponseDto;
import com.temzu.cafefresh.dtos.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

  UserDto findUserByLogin(String login);

  UserDto update(UserDto userDto);

  AuthResponseDto updatePass(String newPass, String currentUser, String token);

  UserDetailsService userDetailsService();
}

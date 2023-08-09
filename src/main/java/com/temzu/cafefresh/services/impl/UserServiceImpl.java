package com.temzu.cafefresh.services.impl;

import com.temzu.cafefresh.dtos.AuthResponseDto;
import com.temzu.cafefresh.dtos.UserDto;
import com.temzu.cafefresh.repositories.UserRepository;
import com.temzu.cafefresh.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDto findUserByLogin(String login) {
    return null;
  }

  @Override
  public UserDto update(UserDto userDto) {
    return null;
  }

  @Override
  public AuthResponseDto updatePass(String newPass, String currentUser, String token) {
    return null;
  }

  @Override
  public UserDetailsService userDetailsService() {
    return username ->
        userRepository
            .findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}

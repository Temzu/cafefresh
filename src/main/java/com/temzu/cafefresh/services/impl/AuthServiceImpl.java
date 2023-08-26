package com.temzu.cafefresh.services.impl;

import com.temzu.cafefresh.daos.UserDao;
import com.temzu.cafefresh.dtos.AuthRequestDto;
import com.temzu.cafefresh.dtos.AuthResponseDto;
import com.temzu.cafefresh.dtos.SignUpRequestDto;
import com.temzu.cafefresh.entities.Role;
import com.temzu.cafefresh.entities.User;
import com.temzu.cafefresh.entities.UserInfo;
import com.temzu.cafefresh.mappers.UserMapper;
import com.temzu.cafefresh.services.AuthService;
import com.temzu.cafefresh.services.TokenService;
import jakarta.transaction.Transactional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserDao userDao;
  private final TokenService tokenService;
  private final UserMapper userMapper;

  @Transactional
  @Override
  public AuthResponseDto signUp(SignUpRequestDto signUpRequestDto) {
    User user = userDao.save(userMapper.toUser(signUpRequestDto));
    String token = returnToken(user);
    return new AuthResponseDto(token);
  }

  @Transactional
  @Override
  public AuthResponseDto login(AuthRequestDto request) {
    User user = userDao.findByEmailAndPassword(request.getEmail(), request.getPassword());
    String token = returnToken(user);
    return new AuthResponseDto(token);
  }

  @Override
  public void logout(String token) {
    tokenService.expireToken(token);
  }

  @Override
  public String returnToken(User user) {
    UserInfo userInfo =
        UserInfo.builder()
            .userId(user.getId())
            .userLogin(user.getLogin())
            .userEmail(user.getEmail())
            .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
            .build();
    return "Bearer " + tokenService.generateTokenWithExpirationTime(userInfo);
  }
}

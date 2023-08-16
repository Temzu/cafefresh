package com.temzu.cafefresh.services.impl;

import com.temzu.cafefresh.daos.UserDao;
import com.temzu.cafefresh.dtos.AuthResponseDto;
import com.temzu.cafefresh.dtos.UserDto;
import com.temzu.cafefresh.mappers.UserMapper;
import com.temzu.cafefresh.repositories.UserRepository;
import com.temzu.cafefresh.services.AuthService;
import com.temzu.cafefresh.services.TokenService;
import com.temzu.cafefresh.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserDao userDao;

  private final UserMapper userMapper;

  private final TokenService tokenService;

  private final AuthService authService;

  @Override
  public UserDto findUserByLogin(String login) {
    return null;
  }

  @Override
  public UserDto update(UserDto userDto) {
    return userMapper.toUserDto(userDao.update(userMapper.toUser(userDto)));
  }

  @Override
  public AuthResponseDto updatePass(String newPass, String currentUser, String token) {
    userDao.updatePass(newPass, currentUser);
    tokenService.expireToken(token);
    return new AuthResponseDto(authService.returnToken(userDao.findByLogin(currentUser))) ;
  }

  @Override
  public UserDetailsService userDetailsService() {
    return new UserDetailsService() {
      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByEmail(username);
      }
    };
  }
}

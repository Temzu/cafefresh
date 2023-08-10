package com.temzu.cafefresh.services;

import com.temzu.cafefresh.entities.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

  String generateTokenWithExpirationTime(UserInfo user);

  String extractUserName(String token);

  boolean isTokenValid(String token, UserDetails userDetails);

  void expireToken(String token);

  UserInfo parseToken(String token);

  Long getUserId(String token);
}

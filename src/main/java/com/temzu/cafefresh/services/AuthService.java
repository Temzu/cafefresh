package com.temzu.cafefresh.services;


import com.temzu.cafefresh.dtos.AuthRequestDto;
import com.temzu.cafefresh.dtos.AuthResponseDto;
import com.temzu.cafefresh.dtos.SignUpRequestDto;
import com.temzu.cafefresh.entities.User;

public interface AuthService {

  AuthResponseDto signUp(SignUpRequestDto signUpRequestDto);

  AuthResponseDto login(AuthRequestDto authRequestDto);

  void logout(String token);

  String returnToken(User user);
}

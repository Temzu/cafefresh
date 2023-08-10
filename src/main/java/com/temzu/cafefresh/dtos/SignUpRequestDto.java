package com.temzu.cafefresh.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequestDto {

  @NotBlank(message = "Login must not be blank and not be null")
  @Size(min = 4, max = 30, message = "Login length must be between 4-30")
  @Pattern(regexp="^[A-Za-z\\d]*$", message = "Login contains invalid characters")
  private String login;

  @NotBlank(message = "Name must not be blank and not be null")
  @Size(min = 3, max = 30, message = "Name length must be between 3-30")
  private String name;

  @NotBlank(message = "Password must not be blank and not be null")
  @Size(min = 6, max = 80, message = "Password length must be between 6-80")
  private String password;

  @NotBlank(message = "Email must not be blank and not be null")
  @Email
  private String email;

  @NotBlank(message = "Phone must not be blank and not be null")
  @Size(min = 11, max = 11, message = "Phone length must be 11")
  @Pattern(regexp="^\\d*$", message = "Phone contains invalid characters")
  private String phone;
}

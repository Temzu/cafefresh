package com.temzu.cafefresh.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryCreateDto {

  @NotBlank(message = "Title must not be blank and not be null")
  @Size(min = 3, max = 20, message = "Title length must be between 3-20")
  @Pattern(regexp="^[А-Яа-я\\s]$", message = "Title contains invalid characters")
  private String title;

}

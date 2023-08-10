package com.temzu.cafefresh.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductCreateDto {

  @NotBlank(message = "Title must not be blank and not be null")
  @Size(min = 3, max = 255, message = "Title length must be between 3-255")
  @Pattern(regexp="^[А-Яа-я\\s\\d]*$", message = "Title contains invalid characters")
  private String title;

  @NotNull(message = "Price must be not null")
  @Min(value = 1, message = "Price must be greater than or equal to 1")
  private BigDecimal price;

  @NotBlank(message = "Category title must not be blank and not be null")
  private String categoryTitle;

}

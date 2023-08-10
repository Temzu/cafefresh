package com.temzu.cafefresh.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderCreateDto {

  @NotBlank(message = "Поле с именем не должно быть пустым")
  @Size(min = 3, max = 30, message = "Длина имени должна быть между 3-30 символами")
  private String clientName;

  @NotBlank(message = "Поле с адресом не должно быть пустым")
  private String address;

  @NotBlank(message = "Поле с номером телефона не должно быть пустым")
  @Size(min = 11, max = 11, message = "Phone length must be 11")
  @Pattern(regexp="^\\d*$", message = "Phone contains invalid characters")
  private String phone;

  @NotNull(message = "Order type must be not null")
  @Min(value = 1, message = "Order type must be greater than or equal to 1")
  @Max(value = 2, message = "Order type must be less than or equal to 2")
  private Integer orderTypeValue;

}

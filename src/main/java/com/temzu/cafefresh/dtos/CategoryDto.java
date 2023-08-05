package com.temzu.cafefresh.dtos;

import lombok.Data;

@Data
public class CategoryDto {

  private Long id;

  private String title;

  private String imageSource;

  private String activeStatus;
}

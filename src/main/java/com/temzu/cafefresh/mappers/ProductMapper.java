package com.temzu.cafefresh.mappers;

import com.temzu.cafefresh.dtos.ProductDto;
import com.temzu.cafefresh.entities.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
public class ProductMapper {

  private final ModelMapper mapper;

  public Product toProduct(ProductDto productDto) {
    return mapper.map(productDto, Product.class);
  }

//  public Product toProduct(ProductCreateDto productCreateDto) {
//    Product product = mapper.map(productCreateDto, Product.class);
//    product.setId(null);
//    return product;
//  }
//
//  public Product toProduct(@Validated ProductUpdateDto productUpdateDto) {
//    return mapper.map(productUpdateDto, Product.class);
//  }

  public ProductDto toProductDto(Product product) {
    return mapper.map(product, ProductDto.class);
  }
}

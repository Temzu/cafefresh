package com.temzu.cafefresh.services;

import com.temzu.cafefresh.dtos.ProductCreateDto;
import com.temzu.cafefresh.dtos.ProductDto;
import com.temzu.cafefresh.dtos.ProductUpdateDto;
import java.util.Set;
import org.springframework.data.domain.Page;

public interface ProductService {

  Page<ProductDto> findPage(Integer page, Integer pageSize);

  Page<ProductDto> findPageByCategoryTitle(String categoryTitle, Integer page, Integer pageSize);

  ProductDto findById(Long id);

  Set<ProductDto> findAll();

  ProductDto save(ProductCreateDto productCreateDto);

  ProductDto update(Long id, ProductUpdateDto productUpdateDto);

  void deleteById(Long id);

  void uploadProductImage(Long id, String imageUrl);

}

package com.temzu.cafefresh.services.impl;

import com.temzu.cafefresh.daos.CategoryDao;
import com.temzu.cafefresh.daos.ProductDao;
import com.temzu.cafefresh.dtos.ProductDto;
import com.temzu.cafefresh.mappers.ProductMapper;
import com.temzu.cafefresh.services.ProductService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductDao productDao;
  private final ProductMapper productMapper;

  private final CategoryDao categoryDao;

  @Override
  public Page<ProductDto> findPage(Integer page, Integer pageSize) {
    return productDao.findPage(page, pageSize).map(productMapper::toProductDto);
  }

  @Override
  public Page<ProductDto> findPageByCategoryTitle(
      String categoryTitle, Integer page, Integer pageSize) {
    return productDao
        .findPageByCategory(categoryDao.findByTitle(categoryTitle), page, pageSize)
        .map(productMapper::toProductDto);
  }

  @Override
  public ProductDto findById(Long id) {
    return productMapper.toProductDto(productDao.findById(id));
  }

  @Override
  public Set<ProductDto> findAll() {
    return productDao.findAll().stream()
        .map(productMapper::toProductDto)
        .collect(Collectors.toSet());
  }

  @Override
  public void deleteById(Long id) {}
}

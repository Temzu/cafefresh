package com.temzu.cafefresh.services.impl;

import com.temzu.cafefresh.daos.CategoryDao;
import com.temzu.cafefresh.daos.ProductDao;
import com.temzu.cafefresh.dtos.ProductCreateDto;
import com.temzu.cafefresh.dtos.ProductDto;
import com.temzu.cafefresh.dtos.ProductUpdateDto;
import com.temzu.cafefresh.entities.Product;
import com.temzu.cafefresh.mappers.ProductMapper;
import com.temzu.cafefresh.services.ProductService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  @Override
  public ProductDto save(ProductCreateDto productCreateDto) {
    Product newProduct = new Product();
    newProduct.setTitle(productCreateDto.getTitle());
    newProduct.setPrice(productCreateDto.getPrice());
    newProduct.setCategory(categoryDao.findByTitle(productCreateDto.getCategoryTitle()));
    newProduct.setActiveStatus(true);
    productDao.saveOrUpdate(newProduct);
    return productMapper.toProductDto(newProduct);
  }

  @Override
  public ProductDto update(Long id, ProductUpdateDto productUpdateDto) {
    Product product = productDao.findById(id);
    product.setTitle(productUpdateDto.getTitle());
    product.setPrice(productUpdateDto.getPrice());
    product.setDescription(productUpdateDto.getDescription());
    Product updatedProduct = productDao.saveOrUpdate(product);
    return productMapper.toProductDto(updatedProduct);
  }

  @Transactional
  @Override
  public void deleteById(Long id) {
    productDao.deleteById(id);
  }

  @Transactional
  @Override
  public void uploadProductImage(Long id, String imageUrl) {
    productDao.findById(id).setImageSource(imageUrl);
  }
}

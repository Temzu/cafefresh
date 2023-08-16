package com.temzu.cafefresh.daos.impl;

import com.temzu.cafefresh.daos.ProductDao;
import com.temzu.cafefresh.entities.Category;
import com.temzu.cafefresh.entities.Product;
import com.temzu.cafefresh.exceptions.ResourceNotFoundException;
import com.temzu.cafefresh.repositories.ProductRepository;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {

  private final ProductRepository productRepository;

  @Override
  public Page<Product> findPage(int page, int pageSize) {
    return productRepository.findAll(PageRequest.of(page - 1, pageSize));
  }

  @Override
  public Page<Product> findPageByCategory(Category category, int page, int pageSize) {
    return productRepository.findAllByCategoryAndActiveStatusTrue(category, PageRequest.of(page - 1, pageSize));
  }

  @Override
  public List<Product> findAllByIdIsIn(List<Long> ids) {
    return productRepository.findAllByIdIsIn(ids);
  }

  @Override
  public Set<Product> findAll() {
    return productRepository.findAllBy();
  }

  @Override
  public Product findById(Long id) {
    return productRepository
        .findById(id)
        .orElseThrow(() -> ResourceNotFoundException.byId(id, Product.class));
  }

  @Override
  public boolean existById(Long id) {
    return productRepository.existsById(id);
  }

  @Override
  public Product saveOrUpdate(Product product) {
    return productRepository.save(product);
  }

  @Override
  public void deleteById(Long id) {
    findById(id).setActiveStatus(false);
  }
}

package com.temzu.cafefresh.daos.impl;

import com.temzu.cafefresh.daos.CategoryDao;
import com.temzu.cafefresh.entities.Category;
import com.temzu.cafefresh.exceptions.ResourceNotFoundException;
import com.temzu.cafefresh.repositories.CategoryRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao {

  private final CategoryRepository categoryRepository;

  @Override
  public Set<Category> findAll() {
    return categoryRepository.findAllBy();
  }

  @Override
  public Page<Category> findPage(int page, int pageSize) {
    return null;
  }

  @Override
  public Page<Category> findPageAll(int page, int pageSize) {
    return null;
  }

  @Override
  public Category findById(Long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> ResourceNotFoundException.byId(id, Category.class));
  }

  @Override
  public Category findByTitle(String title) {
    return null;
  }

  @Override
  public void deleteById(Long id) {}

  @Override
  public Category create(Category category) {
    return null;
  }

  @Override
  public Category update(Category category) {
    return null;
  }
}

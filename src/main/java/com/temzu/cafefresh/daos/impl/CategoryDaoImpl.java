package com.temzu.cafefresh.daos.impl;

import com.temzu.cafefresh.daos.CategoryDao;
import com.temzu.cafefresh.entities.Category;
import com.temzu.cafefresh.exceptions.ResourceNotFoundException;
import com.temzu.cafefresh.repositories.CategoryRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    return categoryRepository.findAllByActiveStatusTrue(PageRequest.of(page - 1, pageSize));
  }

  @Override
  public Page<Category> findPageAll(int page, int pageSize) {
    return categoryRepository.findAll(PageRequest.of(page - 1, pageSize));
  }

  @Override
  public Category findById(Long id) {
    return categoryRepository
        .findById(id)
        .orElseThrow(() -> ResourceNotFoundException.byId(id, Category.class));
  }

  @Override
  public Category findByTitle(String title) {
    return categoryRepository
        .findByTitle(title)
        .orElseThrow(() -> ResourceNotFoundException.byTitle(title, Category.class));
  }

  @Override
  public void deleteById(Long id) {
    findById(id).setActiveStatus(false);
  }

  @Override
  public Category saveOrUpdate(Category category) {
    return categoryRepository.save(category);
  }
}

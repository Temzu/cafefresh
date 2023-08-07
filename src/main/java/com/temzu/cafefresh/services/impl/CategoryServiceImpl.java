package com.temzu.cafefresh.services.impl;

import com.temzu.cafefresh.daos.CategoryDao;
import com.temzu.cafefresh.dtos.CategoryDto;
import com.temzu.cafefresh.mappers.CategoryMapper;
import com.temzu.cafefresh.services.CategoryService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryDao categoryDao;
  private final CategoryMapper categoryMapper;

  @Override
  public Set<CategoryDto> findAll() {
    return categoryDao.findAll().stream()
        .map(categoryMapper::toCategoryDto)
        .collect(Collectors.toSet());
  }

  @Override
  public Page<CategoryDto> findPage(int page, int pageSize) {
    return categoryDao.findPage(page, pageSize).map(categoryMapper::toCategoryDto);
  }

  @Override
  public Page<CategoryDto> findPageAll(int page, int pageSize) {
    return null;
  }

  @Override
  public CategoryDto findById(Long id) {
    return categoryMapper.toCategoryDto(categoryDao.findById(id));
  }

  @Override
  public CategoryDto findByTitle(String title) {
    return null;
  }

  @Override
  public void deleteById(Long id) {}

  @Override
  public void uploadCategoryImage(Long id, String imageUrl) {}
}

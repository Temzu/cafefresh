package com.temzu.cafefresh.services.impl;

import com.temzu.cafefresh.daos.CategoryDao;
import com.temzu.cafefresh.dtos.CategoryCreateDto;
import com.temzu.cafefresh.dtos.CategoryDto;
import com.temzu.cafefresh.dtos.CategoryUpdateDto;
import com.temzu.cafefresh.entities.Category;
import com.temzu.cafefresh.mappers.CategoryMapper;
import com.temzu.cafefresh.services.CategoryService;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    return categoryDao.findPageAll(page, pageSize).map(categoryMapper::toCategoryDto);
  }

  @Override
  public CategoryDto findById(Long id) {
    return categoryMapper.toCategoryDto(categoryDao.findById(id));
  }

  @Transactional
  @Override
  public CategoryDto createCategory(CategoryCreateDto categoryCreateDto) {
    Category newCategory = categoryMapper.toCategory(categoryCreateDto);
    newCategory.setActiveStatus(true);
    return categoryMapper.toCategoryDto(categoryDao.saveOrUpdate(newCategory));
  }

  @Transactional
  @Override
  public void deleteById(Long id) {
    categoryDao.deleteById(id);
  }

  @Override
  public CategoryDto update(CategoryUpdateDto categoryUpdateDto) {
    Category oldCategory = categoryDao.findById(categoryUpdateDto.getId());
    oldCategory.setTitle(categoryUpdateDto.getTitle());
    return categoryMapper.toCategoryDto(categoryDao.saveOrUpdate(oldCategory));
  }

  @Override
  public CategoryDto findByTitle(String title) {
    return null;
  }

  @Transactional
  @Override
  public void uploadCategoryImage(Long id, String imageUrl) {
    categoryDao.findById(id).setImageSource(imageUrl);
  }
}

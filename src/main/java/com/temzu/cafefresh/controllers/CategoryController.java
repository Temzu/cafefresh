package com.temzu.cafefresh.controllers;

import com.temzu.cafefresh.dtos.CategoryDto;
import com.temzu.cafefresh.services.CategoryService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public Page<CategoryDto> findActivePage(
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "page_size", defaultValue = "20") Integer pageSize) {
    if (page < 1 || pageSize < 1) {
      page = 1;
      pageSize = 50;
    }
    return categoryService.findPage(page, pageSize);
  }

  @GetMapping("/all")
  public Set<CategoryDto> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/{id}")
  public CategoryDto findById(@PathVariable Long id) {
    return categoryService.findById(id);
  }
}

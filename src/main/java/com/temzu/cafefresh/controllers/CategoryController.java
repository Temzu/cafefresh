package com.temzu.cafefresh.controllers;

import com.temzu.cafefresh.dtos.CategoryCreateDto;
import com.temzu.cafefresh.dtos.CategoryDto;
import com.temzu.cafefresh.dtos.CategoryUpdateDto;
import com.temzu.cafefresh.dtos.ProductDto;
import com.temzu.cafefresh.services.CategoryService;
import com.temzu.cafefresh.services.ProductService;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private final CategoryService categoryService;

  private final ProductService productService;

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
  public Page<CategoryDto> findPageAll(
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "page_size", defaultValue = "20") Integer pageSize) {
    if (page < 1 || pageSize < 1) {
      page = 1;
      pageSize = 50;
    }
    return categoryService.findPageAll(page, pageSize);
  }

  @GetMapping("/set")
  public Set<CategoryDto> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("/{category_title}")
  public Page<ProductDto> findPageByCategory(
      @PathVariable(name = "category_title") String categoryTitle,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize) {
    if (page < 1 || pageSize < 1) {
      page = 1;
      pageSize = 10;
    }
    return productService.findPageByCategoryTitle(categoryTitle, page, pageSize);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/create")
  @ResponseStatus(value = HttpStatus.CREATED)
  public CategoryDto createCategory(@RequestBody CategoryCreateDto categoryCreateDto) {
    return categoryService.createCategory(categoryCreateDto);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/update")
  public CategoryDto update(@RequestBody CategoryUpdateDto categoryUpdateDto) {
    return categoryService.update(categoryUpdateDto);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable Long id) {
    categoryService.deleteById(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/{id}/upload")
  public void uploadCategoryImage(
      @PathVariable Long id, @RequestParam(name = "imageUrl", required = true) String imageUrl) {
    categoryService.uploadCategoryImage(id, imageUrl);
  }
}

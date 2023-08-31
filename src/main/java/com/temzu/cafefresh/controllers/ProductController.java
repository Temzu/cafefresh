package com.temzu.cafefresh.controllers;

import com.temzu.cafefresh.dtos.CategoryDto;
import com.temzu.cafefresh.dtos.ProductCreateDto;
import com.temzu.cafefresh.dtos.ProductDto;
import com.temzu.cafefresh.dtos.ProductUpdateDto;
import com.temzu.cafefresh.services.CategoryService;
import com.temzu.cafefresh.services.ProductService;
import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

  private final ProductService productService;

  private final CategoryService categoryService;

  @GetMapping
  public Page<ProductDto> findPage(
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize) {
    if (page < 1 || pageSize < 1) {
      page = 1;
      pageSize = 10;
    }
    return productService.findPage(page, pageSize);
  }

  @GetMapping("/categories/{category_title}")
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

  @GetMapping("/categories")
  public Page<CategoryDto> findCategoryPage(
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize) {
    if (page < 1 || pageSize < 1) {
      page = 1;
      pageSize = 10;
    }
    return categoryService.findPage(page, pageSize);
  }

  @GetMapping("/all")
  public Set<ProductDto> findAll() {
    return productService.findAll();
  }

  @GetMapping("/{id}")
  public ProductDto findById(@PathVariable Long id) {
    return productService.findById(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping("/create")
  public ProductDto save(@Valid @RequestBody ProductCreateDto productCreateDto) {
    return productService.save(productCreateDto);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/{id}/update")
  public ProductDto update(@PathVariable Long id, @Valid @RequestBody ProductUpdateDto productUpdateDto) {
    return productService.update(id, productUpdateDto);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/delete/{id}")
  public void deleteById(@PathVariable Long id) {
    productService.deleteById(id);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/{id}/upload")
  public void uploadProductImage(
      @PathVariable Long id, @RequestParam(name = "imageUrl", required = true) String imageUrl) {
    productService.uploadProductImage(id, imageUrl);
  }

}

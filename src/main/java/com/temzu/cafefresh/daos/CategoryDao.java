package com.temzu.cafefresh.daos;


import com.temzu.cafefresh.entities.Category;
import java.util.Set;
import org.springframework.data.domain.Page;

public interface CategoryDao {

  Set<Category> findAll();

  Page<Category> findPage(int page, int pageSize);

  Page<Category> findPageAll(int page, int pageSize);

  Category findById(Long id);

  Category findByTitle(String title);

  void deleteById(Long id);

  Category create(Category category);

  Category update(Category category);

}

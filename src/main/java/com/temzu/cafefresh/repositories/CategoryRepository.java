package com.temzu.cafefresh.repositories;

import com.temzu.cafefresh.entities.Category;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

  Set<Category> findAllBy();

  Page<Category> findAllByActiveStatusTrue(Pageable pageable);
}

package com.temzu.cafefresh.repositories;

import com.temzu.cafefresh.entities.Product;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Set<Product> findAllBy();
}

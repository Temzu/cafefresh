package com.temzu.cafefresh.daos;

import java.util.Set;
import org.springframework.data.domain.Page;

public interface CrudDao<T> {

  Set<T> findAll();

  Page<T> findPage(int page, int pageSize);

  Page<T> findPageAll(int page, int pageSize);

  T findById(Long id);

  void deleteById(Long id);

  T saveOrUpdate(T entity);

}

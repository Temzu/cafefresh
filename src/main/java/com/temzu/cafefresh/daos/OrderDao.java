package com.temzu.cafefresh.daos;

import com.temzu.cafefresh.entities.Order;
import com.temzu.cafefresh.entities.User;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;

public interface OrderDao {

  Page<Order> findPageByUser(User user, int page, int pageSize);

  Order findById(Long id);

  Order saveOrUpdate(Order order);

  List<Order> findAll();

  List<Order> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
}

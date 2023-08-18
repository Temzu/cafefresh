package com.temzu.cafefresh.daos.impl;

import com.temzu.cafefresh.daos.OrderDao;
import com.temzu.cafefresh.dtos.OrderDto;
import com.temzu.cafefresh.entities.Order;
import com.temzu.cafefresh.entities.User;
import com.temzu.cafefresh.exceptions.ResourceNotFoundException;
import com.temzu.cafefresh.reports.OrderReportByDate;
import com.temzu.cafefresh.repositories.OrderRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

  private final OrderRepository orderRepository;

  @Override
  public Page<Order> findPageByUser(User user, int page, int pageSize) {
    return orderRepository.findAllByUser(user, PageRequest.of(page - 1, pageSize));
  }

  @Override
  public Order findById(Long id) {
    return orderRepository
        .findById(id)
        .orElseThrow(() -> ResourceNotFoundException.byId(id, Order.class));
  }

  @Override
  public Order saveOrUpdate(Order order) {
    return orderRepository.save(order);
  }

  @Override
  public List<Order> findAll() {
    return orderRepository.findAll();
  }

  @Override
  public void changeStatus(Long id) {
    Order order = findById(id);
    int newStatus = order.getOrderStatusValue() + 1;
    if (newStatus > 4) {
      newStatus = 4;
    }
    order.setOrderStatusValue(newStatus);
    orderRepository.save(order);
  }

  @Override
  public List<Order> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime to) {
    List<Order> orders = orderRepository.findAllByCreatedAtBetween(from, to);
    if (orders.isEmpty()) {
      throw ResourceNotFoundException.byDate(from, OrderReportByDate.class);
    }
    return orders;
  }
}

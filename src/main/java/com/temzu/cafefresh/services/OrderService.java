package com.temzu.cafefresh.services;

import com.temzu.cafefresh.dtos.OrderCreateDto;
import com.temzu.cafefresh.dtos.OrderDto;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;

public interface OrderService {

  Page<OrderDto> findPageByUserLogin(String login, int page, int pageSize);

  void createOrder(String login, OrderCreateDto orderCreateDto);

  List<OrderDto> findAll();

  void changeStatus(Long id);

  List<OrderDto> findAllByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
}

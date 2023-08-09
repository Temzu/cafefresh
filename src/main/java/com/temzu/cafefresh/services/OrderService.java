package com.temzu.cafefresh.services;

import com.temzu.cafefresh.dtos.OrderCreateDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

public interface OrderService {

  Page<OrderDto> findPageByUserLogin(String login, int page, int pageSize);

  void createOrder(String login, OrderCreateDto orderCreateDto);

  List<OrderDto> findAll();

  void changeStatus(Long id);
}

package com.temzu.cafefresh.mappers;

import com.temzu.cafefresh.dtos.OrderDto;
import com.temzu.cafefresh.entities.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

  private final ModelMapper mapper;

  public OrderDto toOrderDto(Order order) {
    return mapper.map(order, OrderDto.class);
  }
}

package com.temzu.cafefresh.mappers;

import com.temzu.cafefresh.dtos.OrderItemDto;
import com.temzu.cafefresh.entities.OrderItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemMapper {

  private final ModelMapper mapper;

  public OrderItem toOrderItem(OrderItemDto orderItemDto) {
    OrderItem orderItem = mapper.map(orderItemDto, OrderItem.class);
    orderItem.setId(null);
    return orderItem;
  }
}

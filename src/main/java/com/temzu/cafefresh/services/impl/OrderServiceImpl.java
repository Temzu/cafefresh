package com.temzu.cafefresh.services.impl;

import com.temzu.cafefresh.daos.OrderDao;
import com.temzu.cafefresh.daos.UserDao;
import com.temzu.cafefresh.dtos.OrderCreateDto;
import com.temzu.cafefresh.entities.Order;
import com.temzu.cafefresh.entities.OrderItem;
import com.temzu.cafefresh.entities.User;
import com.temzu.cafefresh.enums.OrderStatuses;
import com.temzu.cafefresh.mappers.OrderItemMapper;
import com.temzu.cafefresh.mappers.OrderMapper;
import com.temzu.cafefresh.services.CartService;
import com.temzu.cafefresh.services.OrderService;
import com.temzu.cafefresh.services.RedisService;
import com.temzu.cafefresh.util.Cart;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderDao orderDao;

  private final UserDao userDao;

  private final CartService cartService;

  private final RedisService<Cart> redisService;

  private final OrderItemMapper orderItemMapper;

  private final OrderMapper orderMapper;

  @Transactional(readOnly = true)
  @Override
  public Page<OrderDto> findPageByUserLogin(String login, int page, int pageSize) {
    User curUser = userDao.findByLogin(login);
    return orderDao
        .findPageByUser(curUser, page, pageSize)
        .map(orderMapper::toOrderDto);
  }

  @Transactional
  @Override
  public void createOrder(String login, OrderCreateDto orderCreateDto) {
    Cart cart = cartService.getCurrentCart(cartService.getCartUuidFromSuffix(login));
    Order order =
        Order.builder()
            .phone(orderCreateDto.getPhone())
            .address(orderCreateDto.getAddress())
            .clientName(orderCreateDto.getClientName())
            .orderTypeValue(orderCreateDto.getOrderTypeValue())
            .orderStatusValue(OrderStatuses.ORDER_PROCESSING.getCode())
            .user(userDao.findByLogin(login))
            .price(cart.getPrice())
            .build();

    order.setItems(
        cart.getItems().stream()
            .map(
                oid -> {
                  OrderItem orderItem = orderItemMapper.toOrderItem(oid);
                  orderItem.setOrder(order);
                  return orderItem;
                })
            .collect(Collectors.toList()));
    cart.clear();
    orderDao.saveOrUpdate(order);
    redisService.set(cartService.getCartUuidFromSuffix(login), cart);
  }

  @Transactional(readOnly = true)
  @Override
  public List<OrderDto> findAll() {
    return orderDao.findAll().stream().map(orderMapper::toOrderDto).collect(Collectors.toList());
  }

  @Transactional
  @Override
  public void changeStatus(Long id) {
    orderDao.changeStatus(id);
  }
}

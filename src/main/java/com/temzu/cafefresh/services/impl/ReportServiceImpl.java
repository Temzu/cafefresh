package com.temzu.cafefresh.services.impl;

import com.temzu.cafefresh.daos.OrderDao;
import com.temzu.cafefresh.dtos.OrderDto;
import com.temzu.cafefresh.dtos.OrderItemDto;
import com.temzu.cafefresh.entities.OrderItem;
import com.temzu.cafefresh.exceptions.ResourceNotFoundException;
import com.temzu.cafefresh.reports.OrderReportByDate;
import com.temzu.cafefresh.reports.OrderReportItem;
import com.temzu.cafefresh.services.OrderService;
import com.temzu.cafefresh.services.ReportService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

  private final OrderService orderService;

  @Transactional
  @Override
  public OrderReportByDate generateOrderReportByDate(LocalDate localDate) {
    localDate = localDate.plusDays(1);
    LocalDateTime from = LocalDateTime.of(localDate, LocalTime.MIN);
    LocalDateTime to = LocalDateTime.of(localDate, LocalTime.MAX);


    List<OrderDto> orders = orderService.findAllByCreatedAtBetween(from, to);

    BigDecimal sum = getSum(orders);

    BigDecimal averageCheck = sum.divide(BigDecimal.valueOf(orders.size()), RoundingMode.DOWN);

    List<OrderReportItem> orderReportItems = getOrderReportItems(
        orders);

    return OrderReportByDate.builder()
        .items(orderReportItems)
        .sum(sum)
        .averageCheck(averageCheck)
        .build();
  }

  private BigDecimal getSum(List<OrderDto> orders) {
    return orders.stream()
        .map(OrderDto::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  private List<OrderReportItem> getOrderReportItems(List<OrderDto> orders) {
    Map<String, Integer> collect = orders.stream()
        .map(OrderDto::getItems)
        .flatMap(Collection::stream)
        .collect(
            Collectors.groupingBy(
                OrderItemDto::getProductTitle,
                Collectors.summingInt(OrderItemDto::getQuantity)));

    return collect.entrySet().stream()
        .map(entry -> new OrderReportItem(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());
  }
}

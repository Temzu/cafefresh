package com.temzu.cafefresh.controllers;

import com.temzu.cafefresh.dtos.OrderCreateDto;
import com.temzu.cafefresh.dtos.OrderDto;
import com.temzu.cafefresh.services.OrderService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.Hashtable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
@Slf4j
public class OrderController {

  private final OrderService orderService;

  @GetMapping
  public Page<OrderDto> findPageByCurrentUser(
      Principal principal,
      @RequestParam(name = "page", defaultValue = "1") int page,
      @RequestParam(name = "page_size", defaultValue = "10") int pageSize
  ) {
    if (page < 1 || pageSize < 1) {
      page = 1;
      pageSize = 10;
    }
    return orderService.findPageByUserLogin(principal.getName(), page, pageSize);
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public void createOrder(Principal principal, @Valid @RequestBody OrderCreateDto orderCreateDto) {
    orderService.createOrder(principal.getName(), orderCreateDto);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
  @GetMapping("/find_all")
  public List<OrderDto> findAll() {
    return orderService.findAll();
  }

  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
  @PostMapping("/{id}/change_status")
  public void changeNextStatus(@PathVariable Long id) {
    orderService.changeStatusOnNext(id);
  }
}

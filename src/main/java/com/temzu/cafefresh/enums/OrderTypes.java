package com.temzu.cafefresh.enums;

import com.temzu.cafefresh.exceptions.ResourceNotFoundException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum OrderTypes {
  DELIVERY(1, "Доставка"),
  PICKUP(2, "Самовывоз");

  private final long code;
  private final String title;

  OrderTypes(int code, String title) {
    this.code = code;
    this.title = title;
  }

  public static OrderTypes of(long code) {
    return Arrays.stream(values())
        .filter(orderType -> orderType.code == code)
        .findFirst()
        .orElseThrow(() -> ResourceNotFoundException.byId(code, OrderTypes.class));
  }

  public static boolean contains(int code) {
    return Arrays.stream(values()).anyMatch(orderType -> orderType.code == code);
  }

  @Override
  public String toString() {
    return this.title;
  }
}

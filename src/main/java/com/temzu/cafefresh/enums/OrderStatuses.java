package com.temzu.cafefresh.enums;

import com.temzu.cafefresh.exceptions.ResourceNotFoundException;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum OrderStatuses {

  ORDER_PROCESSING(1, "Обрабатывается"),
  ORDER_PREPARATION(2, "Готовится"),
  ORDER_DELIVERY(3, "Доставляется"),
  ORDER_CLOSE(4, "Закрыт");

  private final long code;
  private final String title;

  OrderStatuses(int code, String title) {
    this.code = code;
    this.title = title;
  }

  public static OrderStatuses of(long code) {
    return Arrays.stream(values())
        .filter(orderStatus -> orderStatus.code == code)
        .findFirst()
        .orElseThrow(() -> ResourceNotFoundException.byId(code, OrderTypes.class));
  }

  public static boolean contains(int code) {
    return Arrays.stream(values()).anyMatch(offerStatus -> offerStatus.code == code);
  }

  @Override
  public String toString() {
    return this.title;
  }

}

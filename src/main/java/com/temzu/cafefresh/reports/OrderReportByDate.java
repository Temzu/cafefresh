package com.temzu.cafefresh.reports;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderReportByDate {

  private List<OrderReportItem> items;

  private BigDecimal averageCheck;

  private BigDecimal sum;

}

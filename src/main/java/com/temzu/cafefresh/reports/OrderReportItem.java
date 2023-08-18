package com.temzu.cafefresh.reports;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReportItem {

  private String productName;

  private Integer quantity;

}

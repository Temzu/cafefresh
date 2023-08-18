package com.temzu.cafefresh.services;

import com.temzu.cafefresh.reports.OrderReportByDate;
import java.time.LocalDate;

public interface ReportService {

  OrderReportByDate generateOrderReportByDate(LocalDate localDate);
}

package com.bionic.erestaurant.service;

import java.sql.Timestamp;
import java.util.List;

import com.bionic.erestaurant.core.reports.ReportByDateResult;
import com.bionic.erestaurant.entity.Orders;

public interface OrderService {
	public Orders getOrderById(int id);
	public void createOrder(Orders order);
	public void modifyOrder(Orders order);
	public List<Orders> getOrderByUser(int user_id);
	public List<ReportByDateResult> getOrderReportByTotal(Timestamp dateFrom, Timestamp dateTo);

}

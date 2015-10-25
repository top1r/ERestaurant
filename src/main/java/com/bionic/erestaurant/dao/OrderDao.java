package com.bionic.erestaurant.dao;

import java.sql.Timestamp;
import java.util.List;

import com.bionic.erestaurant.core.reports.ReportByDateResult;
import com.bionic.erestaurant.entity.Orders;

public interface OrderDao {
	public Orders getOrderById(int id);
	public void createOrder(Orders order);
	public List<Orders> getOrderByUser(int user_id);
	public List<ReportByDateResult> getOrderReportByTotal(Timestamp dateFrom, Timestamp dateTo);
	public void modifyOrder(Orders order);
}

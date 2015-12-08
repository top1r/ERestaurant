package com.bionic.erestaurant.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.core.reports.ReportByDateResult;
import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Orders;
import com.bionic.erestaurant.entity.Users;

public interface OrderService {
	public Orders getOrderById(int id);
	public void createOrder(Orders order);
	public void saveOrder(Orders order);
	public List<Orders> getOrderByUser(int user_id);
	public List<ReportByDateResult> getOrderReportByTotal(String dateFrom, String dateTo);
	public Orders getLastUsersOrderByAddress(Users user, int address_id);
	public List<Orders> getDeliveryPendingList();
	public void moveThroughWorkflow(Orders order);
}

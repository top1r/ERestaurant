package com.bionic.erestaurant.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.bionic.erestaurant.core.reports.ReportByDateResult;
import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Orders;
import com.bionic.erestaurant.entity.Users;

public interface OrderDao {
	public Orders getOrderById(int id);
	public void createOrder(Orders order);
	public List<Orders> getOrderByUser(int user_id);
	public List<ReportByDateResult> getOrderReportByTotal(String dateFrom, String dateTo);
	public void saveOrder(Orders order);
	public Orders getLastUsersOrderByAddress(Users user, Address address);
}

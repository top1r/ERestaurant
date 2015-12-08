package com.bionic.erestaurant.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.core.reports.ReportByDateResult;
import com.bionic.erestaurant.dao.OrderDao;
import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Orders;
import com.bionic.erestaurant.entity.Orders.OrderStatus;
import com.bionic.erestaurant.entity.Users;

@Named
public class OrderServiceImpl implements OrderService {
	@Inject
	private OrderDao orderDao;
	public Orders getOrderById(int id){
		return orderDao.getOrderById(id);
	}
	
	@Transactional
	public void createOrder(Orders order){
		orderDao.createOrder(order);
	}
	
	@Transactional
	public void saveOrder(Orders order){
		orderDao.saveOrder(order);
	}
	
	public List<Orders> getOrderByUser(int user_id) {
		return orderDao.getOrderByUser(user_id);
	}
	
	public List<ReportByDateResult> getOrderReportByTotal(String dateFrom, String dateTo){
		return orderDao.getOrderReportByTotal(dateFrom, dateTo);
	}
	
	public Orders getLastUsersOrderByAddress(Users user, int address_id){
		return orderDao.getLastUsersOrderByAddress(user, address_id);
	}
	
	public List<Orders> getDeliveryPendingList(){
		return orderDao.getDeliveryPendingList();
	}
	
	@Transactional
	public void moveThroughWorkflow(Orders order){
		String status = order.getStatus();
		status = OrderStatus.values()[(OrderStatus.valueOf(status).ordinal() + 1)].toString();
		order.setStatus(status);
		orderDao.saveOrder(order);
	}

	
}

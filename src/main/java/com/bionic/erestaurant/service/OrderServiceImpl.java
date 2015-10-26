package com.bionic.erestaurant.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.core.reports.ReportByDateResult;
import com.bionic.erestaurant.dao.OrderDao;
import com.bionic.erestaurant.entity.Orders;

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
	
	public List<ReportByDateResult> getOrderReportByTotal(Date dateFrom, Date dateTo){
		return orderDao.getOrderReportByTotal(dateFrom, dateTo);
	}
	
}

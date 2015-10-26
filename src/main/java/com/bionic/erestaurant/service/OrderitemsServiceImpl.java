package com.bionic.erestaurant.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.Order;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.dao.OrderitemsDao;
import com.bionic.erestaurant.entity.Orderitems;

@Named
public class OrderitemsServiceImpl implements OrderitemsService{
	@Inject
	private OrderitemsDao orderitemsDao;
	public Orderitems getOrderitemsById(int id){
		return orderitemsDao.getOrderitemsById(id);
	}
	
	@Transactional
	public void saveOrderItem(Orderitems o){
		orderitemsDao.saveOrderItem(o);
	}
	
	@Transactional
	public void createOrderItem(Orderitems o){
		orderitemsDao.createOrderItem(o);
	}
	
	public List<Orderitems> getKitchenPendingList(){
		//TODO
		return orderitemsDao.getKitchenPendingList();
	}
}

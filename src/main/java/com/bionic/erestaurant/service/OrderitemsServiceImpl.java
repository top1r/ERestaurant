package com.bionic.erestaurant.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.criteria.Order;

import com.bionic.erestaurant.dao.OrderitemsDao;
import com.bionic.erestaurant.entity.Orderitems;

@Named
public class OrderitemsServiceImpl implements OrderitemsService{
	@Inject
	private OrderitemsDao orderitemsDao;
	public Orderitems getOrderitemsById(int id){
		return orderitemsDao.getOrderitemsById(id);
	}
	
	public void saveOrderItem(Orderitems o){
		orderitemsDao.saveOrderItem(o);
	}
	
	public void createOrderItem(Orderitems o){
		orderitemsDao.createOrderItem(o);
	}
	
	public List<Orderitems> getKitchenPendingList(){
		//TODO
		return orderitemsDao.getKitchenPendingList();
	}
}

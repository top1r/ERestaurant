package com.bionic.erestaurant.service;

import javax.inject.Inject;
import javax.inject.Named;

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
}

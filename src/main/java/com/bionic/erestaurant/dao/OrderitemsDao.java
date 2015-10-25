package com.bionic.erestaurant.dao;

import java.util.List;

import com.bionic.erestaurant.entity.Orderitems;

public interface OrderitemsDao {
	public Orderitems getOrderitemsById(int id);
	public void createOrderItem(Orderitems o);
	public void saveOrderItem(Orderitems o);
	public List<Orderitems> getKitchenPendingList();
}

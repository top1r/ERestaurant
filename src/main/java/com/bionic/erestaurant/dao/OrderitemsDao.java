package com.bionic.erestaurant.dao;

import com.bionic.erestaurant.entity.Orderitems;

public interface OrderitemsDao {
	public Orderitems getOrderitemsById(int id);
	public void createOrderItem(Orderitems o);
}

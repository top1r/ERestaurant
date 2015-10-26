package com.bionic.erestaurant.service;

import com.bionic.erestaurant.entity.Orderitems;

public interface OrderitemsService {
	public Orderitems getOrderitemsById(int id);
	public void saveOrderItem(Orderitems o);
}

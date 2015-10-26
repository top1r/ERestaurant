package com.bionic.erestaurant.core;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.entity.Orderitems;
import com.bionic.erestaurant.entity.Orderitems.orderitemsStatus;
import com.bionic.erestaurant.entity.Orders.OrderStatus;
import com.bionic.erestaurant.service.OrderService;
import com.bionic.erestaurant.service.OrderitemsService;
import com.bionic.erestaurant.service.UserService;

public class KitchenServiceImpl implements KitchenService{
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	OrderService orderService = (OrderService)context.getBean("orderServiceImpl");
	OrderitemsService orderitemsService = (OrderitemsService)context.getBean("orderitemsServiceImpl");
	
	public KitchenServiceImpl(){}
	
	@Transactional
	public void allocateInventory(Orderitems oi){
		if (oi.getAllocated() <= oi.getQuantity()){
			if (oi.getAllocated() < oi.getQuantity()){
				oi.setAllocated(oi.getAllocated() + 1);
			} 
			if (oi.getAllocated() == oi.getQuantity()){
				oi.setStatus(orderitemsStatus.DONE.toString());	
				oi.setLastupdated(Timestamp.valueOf(LocalDateTime.now()));
			orderitemsService.saveOrderItem(oi);
			boolean orderDone = true;
			for (Orderitems oia: oi.getOrder().getOrderitems()){
				System.out.println(oia.getStatus());
				if (!oia.getStatus().equals(orderitemsStatus.DONE.toString())){
					orderDone = false;
					System.out.println("Order is not ready yet");
					break;
				}
			};
			if (orderDone){
				oi.getOrder()
					.setStatus(OrderStatus.READY_FOR_SHIPMENT.toString());
				oi.getOrder()
					.setLastupdated(Timestamp.valueOf(LocalDateTime.now()));
				orderService.saveOrder(oi.getOrder());
			}
		}
	}
}
}

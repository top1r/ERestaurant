package com.bionic.erestaurant.core;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.entity.Orderitems;
import com.bionic.erestaurant.entity.Orderitems.orderitemsStatus;
import com.bionic.erestaurant.entity.Orders.OrderStatus;
import com.bionic.erestaurant.service.OrderService;
import com.bionic.erestaurant.service.OrderitemsService;
import com.bionic.erestaurant.service.ProductService;
import com.bionic.erestaurant.service.UserService;

public class KitchenServiceImpl implements KitchenService{
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	OrderService orderService = (OrderService)context.getBean("orderServiceImpl");
	OrderitemsService orderitemsService = (OrderitemsService)context.getBean("orderitemsServiceImpl");
	ProductService productService = (ProductService)context.getBean("productServiceImpl");	

	public KitchenServiceImpl(){}
	
	@Transactional
	public void allocateInventory(Orderitems oi){
		if (oi.getAllocated() <= oi.getQuantity()){
			if (oi.getAllocated() < oi.getQuantity()){
				oi.setAllocated(oi.getAllocated() + 1);
				orderitemsService.saveOrderItem(oi);
			} 
			if (oi.getAllocated() == oi.getQuantity()){
				oi.setStatus(orderitemsStatus.DONE.toString());	
				oi.setLastupdated(Timestamp.valueOf(LocalDateTime.now()));
			orderitemsService.saveOrderItem(oi);
			boolean orderKitchenDone = true;
			List<Orderitems> nonKitchen = new ArrayList<Orderitems>();
			for (Orderitems oia: oi.getOrder().getOrderitems()){
				System.out.println(oia.getStatus());
				if ((!oia.getStatus().equals(orderitemsStatus.DONE.toString()))) {
					System.out.println("Kitchen is: " + productService.getProductById(oia.getProductId()).isKitchen());
					if (productService.getProductById(oia.getProductId()).isKitchen()){
						orderKitchenDone = false;
						System.out.println("Kitchen order is not ready yet");
						break;
					} else {nonKitchen.add(oia);}
				} 
			};
			if (orderKitchenDone){
				for (Orderitems oin: nonKitchen){
					for (int qnt = 1; qnt <= oin.getQuantity(); qnt++){
						this.allocateInventory(oin);
					}				
				}
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

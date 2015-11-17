package com.bionic.erestaurant.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.entity.Orderitems;
import com.bionic.erestaurant.entity.Orderitems.orderitemsStatus;
import com.bionic.erestaurant.entity.Orders.OrderStatus;
import com.bionic.erestaurant.service.OrderService;
import com.bionic.erestaurant.service.OrderitemsService;
import com.bionic.erestaurant.service.ProductService;

@Named
@Scope("session")
public class KitchenBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Orderitems> kitchenList;
	
	@Autowired
	private OrderitemsService orderitemsService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;

	public KitchenBean(){
		kitchenList = new ArrayList<Orderitems>();
	}
	
	public void getKitchenPendingList(){
		kitchenList = orderitemsService.getKitchenPendingList();
	} 
	
	public String getProductNameById(int id){
		return productService.getProductById(id).getName();
	}
	
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

	public List<Orderitems> getKitchenList() {
		return kitchenList;
	}

	public void setKitchenList(List<Orderitems> kitchenList) {
		this.kitchenList = kitchenList;
	}
}

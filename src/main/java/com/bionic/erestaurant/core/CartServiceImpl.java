package com.bionic.erestaurant.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bionic.erestaurant.entity.*;
import com.bionic.erestaurant.service.AddressService;
import com.bionic.erestaurant.service.OrderService;
import com.bionic.erestaurant.service.ProductService;
import com.bionic.erestaurant.service.UserService;

public class CartServiceImpl implements CartService {
	private ArrayList<Product> productList = new ArrayList<Product>();
	
	/* Hardcoded user, while the session context is not yet defined */
	private Users user;
	
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	UserService userService = (UserService)context.getBean("userServiceImpl");
	AddressService addressService = (AddressService)context.getBean("addressServiceImpl");
	OrderService orderService = (OrderService)context.getBean("orderServiceImpl");	
	ProductService productService = (ProductService)context.getBean("productServiceImpl");	

	
	public CartServiceImpl() {};
	
	public void addProduct(Product product) {
		productList.add(product);
		System.out.println(productList.size());
	}

	public void removeProduct(Product product) {
		if (productList.contains(product)) {
			productList.remove(productList.indexOf(product));		
		} else {
			//Logger needs to be added here
		}		
	}
	
	public void submit() {
		if (productList.size() > 0){
			Orders order = new Orders();			
			Collection <Orderitems> orderitemsList = new ArrayList<Orderitems>();
			for (Product p: productList){
				Orderitems item = new Orderitems(p.getProductId());
				System.out.println(orderitemsList.contains(item));
				if (orderitemsList.contains(item)){
					Orderitems originalItem = orderitemsList
							.stream()
							.filter(oi-> oi.equals(item))
							.findFirst()
							.get();
					originalItem.setQuantity(originalItem.getQuantity() + item.getQuantity());
					//System.out.println(item.getQuantity());
					item.setQuantity(1);
				} else {
					orderitemsList.add(item);
				}
			}
						
			/* Hardcoded user, while the session context is not yet defined */
			Users testUser = userService.getByEmail("test2@test1.com");
			order.setUserId(testUser.getUserId());
			order.setAddressId(addressService
											.getAddressesByUser(testUser)
											.get(0)
											.getAddressId());
			order.setOrderitems((List<Orderitems>)orderitemsList);
			boolean negativeInventory = false;
			for (Orderitems oi: orderitemsList){
				System.out.println(oi.getQuantity());
				oi.setOrder(order);
				Product pn = productService.getProductById(oi.getProductId());
				//Check if there is enough inventory for the order
				int deltaQuantity = pn.getQuantity() - oi.getQuantity();
				if (deltaQuantity > 0){
					pn.setQuantity(deltaQuantity);
					productService.saveProduct(pn);
				} else if (deltaQuantity == 0) {
					pn.setQuantity(deltaQuantity);
					pn.setOnline(false);
				} else {
					negativeInventory = true;
					break;
				}
			}
			if (!negativeInventory) {
				orderService.createOrder(order);
			} else {
				System.out.println("Not enough inventory");
			}
			
		} else {
			//Logger goes here
		}
	}

	public List<Product> getProductList() {
		return productList;
	}
	
	public double getCartTotal(){
		double sum = 0;
		for (Product p: productList){
			sum += p.getPrice();
		}
		return sum;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public Users getUser() {
		return user;
	}
	
}

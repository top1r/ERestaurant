package com.bionic.erestaurant.bean;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bionic.erestaurant.entity.*;
import com.bionic.erestaurant.service.AddressService;
import com.bionic.erestaurant.service.OrderService;
import com.bionic.erestaurant.service.ProductService;

@Named
@Scope("session")
@SessionAttributes("address")
public class CartBean  {
	private Map<Product, Integer> productMap;
	
    FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
/*
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	AddressService addressService = (AddressService)context.getBean("addressServiceImpl");
	OrderService orderService = (OrderService)context.getBean("orderServiceImpl");	
	ProductService productService = (ProductService)context.getBean("productServiceImpl");	
*/
	
	public CartBean() {
		productMap = new HashMap<Product, Integer>();
	};
	
	public void addProduct(Product product) {
		if (productMap.containsKey(product)){			
			productMap.put(product, productMap.get(product) + 1);
		} else {
			productMap.put(product, 1);
		}
		System.out.println("ADD: "+ productMap.size());

	}
	
	public List<Entry<String, Integer>> getCartProductsList() {
        return new ArrayList(productMap.entrySet());
        }

	public Map<Product, Integer> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<Product, Integer> productMap) {
		this.productMap = productMap;
	}


	public void removeProduct(Product product) {
		if (productMap.containsKey(product)) {
			productMap.remove(product);
		} else {
			//Logger needs to be added here
		}		
		System.out.println("Remove: "+ productMap.size());

	}
	
	public void increaseQuantity(Product product){
		if (productMap.containsKey(product)) {
			productMap.put(product, productMap.get(product) + 1);
		} else {
			//Logger needs to be added here
		}
		System.out.println("Increase: "+ productMap.size());

	}
	
	public int getTotalItemsInCart(){
		return productMap.values()
			.stream()
			.mapToInt(i->i.intValue())
			.sum();
	}
	
	public void decreaseQuantity(Product product){
		if (productMap.containsKey(product)) {
			int quantity = productMap.get(product); 
			if (quantity > 1){
				productMap.put(product, productMap.get(product) - 1);
			} else {
				this.removeProduct(product);
			}
		} else {
			//Logger needs to be added here
		}
		System.out.println("Decrease: "+ productMap.size());

	}
	
	public double getCartTotal(){
		double sum = 0;
		for (Product p: productMap.keySet()){
			sum += p.getPrice() * productMap.get(p);
		}
		return sum;
	}
	
	public String cartRedirect(){
		return "cart";
	}
	
	public String orderRedirect(){
		return "confirmation";
	}
	
	public String submit() {
		if (!productMap.isEmpty()){
			Orders order = new Orders();			
			Collection <Orderitems> orderitemsList = new ArrayList<Orderitems>();

			for (Product p: productMap.keySet()){
				Orderitems item = new Orderitems(p.getProductId(), productMap.get(p));
				System.out.println(orderitemsList.contains(item));
				orderitemsList.add(item);
			}
							
			Users user = (Users)session.getAttribute("user"); 
			order.setUserId(user.getUserId());
			Address address = (Address)session.getAttribute("address");
			System.out.println(address);
			order.setAddressId(address.getAddressId());
			order.setOrderitems((List<Orderitems>)orderitemsList);
			boolean negativeInventory = false;
			for (Orderitems oi: orderitemsList){
				System.out.println(oi.getQuantity());
				oi.setOrder(order);
				Product pn = productService.getProductById(oi.getProductId());
				//Check if there is enough inventory for the order
				int deltaQuantity = pn.getQuantity() - oi.getQuantity();
				System.out.println("Delta: " + deltaQuantity);
				if (deltaQuantity > 0){
					pn.setQuantity(deltaQuantity);
					//productService.saveProduct(pn);
				} else if (deltaQuantity == 0) {
					pn.setQuantity(deltaQuantity);
					pn.setOnline(false);
				} else {
					negativeInventory = true;
					break;
				}
				productService.saveProduct(pn);
			}
			if (!negativeInventory) {
				orderService.createOrder(order);
				//session.removeAttribute("address");
				productMap.clear();
				return this.orderRedirect();
			} else {
				//TODO Logging
				System.out.println("Not enough inventory");
				return this.cartRedirect();
			}
			
		} else {
			return this.cartRedirect();
			//Logger goes here
		}
	}
}

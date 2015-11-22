package com.bionic.erestaurant.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bionic.erestaurant.core.AddressHelper;
import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Orders;
import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.service.AddressService;
import com.bionic.erestaurant.service.OrderService;

@Named
@Scope("session")
public class OrderBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Orders order;
	private List<Orders> deliveryList;


	public List<Orders> getDeliveryList() {
		return deliveryList;
	}

	public void setDeliveryList(List<Orders> deliveryList) {
		this.deliveryList = deliveryList;
	}
	
	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	@Inject
	private OrderService orderService;
	
	public OrderBean() {
		order = new Orders();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		//session.setAttribute("address", address);
	}
	
	public String orderRedirect(){
		return "home";
	}
	
	public void moveThroughWorkflow(Orders order){
		orderService.moveThroughWorkflow(order);
	}
	
	public int getOrderForConfirmation(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		Users user = (Users) session.getAttribute("user");
		Address address = (Address) session.getAttribute("address");
		int order_id = orderService.getLastUsersOrderByAddress(user, address).getOrderId();
		//session.removeAttribute("address");
		return order_id;
	}
	
	public void getDeliveryPendingList(){
		deliveryList = orderService.getDeliveryPendingList();
	}

}

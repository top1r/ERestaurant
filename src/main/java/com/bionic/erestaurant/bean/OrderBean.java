package com.bionic.erestaurant.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;

import javax.el.ELContext;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Orders;
import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.service.OrderService;

@Named
@Scope("session")
public class OrderBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Orders order;
	private List<Orders> deliveryList;
	private boolean sortDateAscending = true;    
	private boolean sortStatusAscending = true;
	private boolean sortDate = true;


	public boolean isSortDateAscending() {
		return sortDateAscending;
	}

	public void setSortDateAscending(boolean sortDateAscending) {
		this.sortDateAscending = sortDateAscending;
	}

	public boolean isSortStatusAscending() {
		return sortStatusAscending;
	}

	public void setSortStatusAscending(boolean sortStatusAscending) {
		this.sortStatusAscending = sortStatusAscending;
	}

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
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		AddressBean addressBean 
	    = (AddressBean) FacesContext.getCurrentInstance().getApplication()
	    .getELResolver().getValue(elContext, null, "addressBean");
		int order_id = orderService.getLastUsersOrderByAddress(user, addressBean.getAddressId()).getOrderId();
		//session.removeAttribute("address");
		return order_id;
	}
	
	public String sortByDateCreated(){
		if (sortDateAscending){
			Collections.sort(deliveryList, new Comparator<Orders>() {

				@Override
				public int compare(Orders o1, Orders o2) {
					
					return o1.getTimeplaced().compareTo(o2.getTimeplaced());
						
				}

			});
		} else {
			Collections.sort(deliveryList, new Comparator<Orders>() {

				@Override
				public int compare(Orders o1, Orders o2) {
						
					return o2.getTimeplaced().compareTo(o1.getTimeplaced());
				}

			});
		}
		
		return null;
	}
	
	public void setStatusOrder(){
		if (sortStatusAscending){
			sortStatusAscending = false;
		} else {
			sortStatusAscending = true;
		}
		sortDate = false;
	}
	
	public boolean isSortDate() {
		return sortDate;
	}

	public void setSortDate(boolean sortDate) {
		this.sortDate = sortDate;
	}

	public void setDateOrder(){
		if (sortDateAscending){
			sortDateAscending = false;
		} else {
			sortDateAscending = true;
		}	
		sortDate = true;
	}
	
	
	
	public String sortByStatus(){
		if (sortStatusAscending){
			Collections.sort(deliveryList, new Comparator<Orders>() {

				@Override
				public int compare(Orders o1, Orders o2) {
					
					return o1.getStatus().compareTo(o2.getStatus());
						
				}

			});
		} else {
			Collections.sort(deliveryList, new Comparator<Orders>() {

				@Override
				public int compare(Orders o1, Orders o2) {
						
					return o2.getStatus().compareTo(o1.getStatus());
				}

			});
		}
		
		return null;
	}
	
	public void getDeliveryPendingList(){
		deliveryList = orderService.getDeliveryPendingList();
		if (!sortDate){
			this.sortByStatus();
		} else if (sortDate){
			this.sortByDateCreated();
		}
	}

}

package com.bionic.erestaurant.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
	private List<Orderitems> orderitems;
	
	public enum OrderStatus {
		NEW, KITCHEN_DONE, NON_KITCHEN_DONE, READY_FOR_SHIPMENT, DELIVERING, DELIVERED
	}

	private int user_id;
	private int address_id;
	private String status;
	private Timestamp timeplaced;
	private Timestamp lastupdated;
	
	public Orders() {
		this.status = OrderStatus.NEW.toString();
		this.timeplaced = this.lastupdated = Timestamp.valueOf(LocalDateTime.now());
	}

	public int getOrderId() {
		return order_id;
	}

	public void setOrderId(int order_id) {
		this.order_id = order_id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public int getAddressId() {
		return address_id;
	}

	public void setAddressId(int address_id) {
		this.address_id = address_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTimeplaced() {
		return timeplaced;
	}

	public void setTimeplaced(Timestamp timeplaced) {
		this.timeplaced = timeplaced;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public Collection<Orderitems> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<Orderitems> orderitems) {
		this.orderitems = orderitems;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}
	
}

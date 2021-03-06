package com.bionic.erestaurant.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Orderitems {
	public enum orderitemsStatus{
		NEW, DONE
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderitem_id;

	@ManyToOne
	@JoinColumn(name="order_id")
	private Orders order;

	private int product_id;
	private int quantity;
	private int allocated;
	private String status;
	private Timestamp created;
	private Timestamp lastupdated;
	
	
	
	public Orderitems(int product_id, int quantity) {
		this.product_id = product_id;
		this.quantity = quantity;
		this.allocated = 0;
		this.status = orderitemsStatus.NEW.toString();
		this.created = this.lastupdated = Timestamp.valueOf(LocalDateTime.now());
	}

	public Orderitems() {}

	public int getOrderitemId() {
		return orderitem_id;
	}

	public void setOrderitemId(int orderitem_id) {
		this.orderitem_id = orderitem_id;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public int getProductId() {
		return product_id;
	}

	public void setProductId(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getLastupdated() {
		return lastupdated;
	}

	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + product_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orderitems other = (Orderitems) obj;
		if (product_id != other.product_id)
			return false;
		return true;
	}

	public int getAllocated() {
		return allocated;
	}

	public void setAllocated(int allocated) {
		this.allocated = allocated;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public int getOrderitem_id() {
		return orderitem_id;
	}

	public void setOrderitem_id(int orderitem_id) {
		this.orderitem_id = orderitem_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
}

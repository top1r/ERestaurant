package com.bionic.erestaurant.core.reports;

import java.sql.Timestamp;

public class ReportByDateResult {
	int orderId; 
//	long orderitemsCount;
	double orderTotal;
	Timestamp timestamp;
	
	public ReportByDateResult(){};
	
	
	public ReportByDateResult(int orderId, long orderitemsCount, double orderTotal, Timestamp timestamp) {
		this.orderId = orderId;
//		this.orderitemsCount = orderitemsCount;
		this.orderTotal = orderTotal;
		this.timestamp = timestamp;
	}


	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
/*	
	public long getOrderitemsCount() {
		return orderitemsCount;
	}
	public void setOrderitemsCount(int orderitemsCount) {
		this.orderitemsCount = orderitemsCount;
	}
*/
	public double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	@Override
	public String toString() {
		return "ReportByDateResult [orderId=" + orderId + ", orderTotal="
				+ orderTotal + ", timestamp=" + timestamp + "]";
	}	
}

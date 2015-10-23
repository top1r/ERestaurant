package com.bionic.erestaurant.core.reports;

import java.sql.Timestamp;

public class ReportByCategoryResult {
	String categoryName; 
//	long orderitemsCount;
	double categoryTotal;
	Timestamp timestamp;
	
	public ReportByCategoryResult(){};
	
	
	public ReportByCategoryResult(String categoryName, long orderitemsCount, double orderTotal, Timestamp timestamp) {
		this.categoryName = categoryName;
//		this.orderitemsCount = orderitemsCount;
		this.categoryTotal = orderTotal;
		this.timestamp = timestamp;
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
		return categoryTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.categoryTotal = orderTotal;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public double getCategoryTotal() {
		return categoryTotal;
	}


	public void setCategoryTotal(double categoryTotal) {
		this.categoryTotal = categoryTotal;
	}


	@Override
	public String toString() {
		return "ReportByCategoryResult [categoryName=" + categoryName + ", categoryTotal=" + categoryTotal
				+ ", timestamp=" + timestamp + "]";
	}	
}

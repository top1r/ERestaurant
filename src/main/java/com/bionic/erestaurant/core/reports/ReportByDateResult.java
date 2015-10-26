package com.bionic.erestaurant.core.reports;

import java.sql.Date;
import java.sql.Timestamp;

public class ReportByDateResult {
	long ordersCount;
	double ordersTotal;
	Date date;
	
	public ReportByDateResult(){}

	public ReportByDateResult(Date date, long ordersCount, double ordersTotal) {
		super();
		this.ordersCount = ordersCount;
		this.ordersTotal = ordersTotal;
		this.date = date;
	}

	public long getOrdersCount() {
		return ordersCount;
	}

	public void setOrdersCount(long ordersCount) {
		this.ordersCount = ordersCount;
	}

	public double getOrdersTotal() {
		return ordersTotal;
	}

	public void setOrdersTotal(double ordersTotal) {
		this.ordersTotal = ordersTotal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ReportByDateResult [ordersCount=" + ordersCount + ", ordersTotal=" + ordersTotal + ", date=" + date
				+ "]";
	};
	
	

}

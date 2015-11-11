package com.bionic.erestaurant.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import com.bionic.erestaurant.core.reports.ReportByDateResult;
import com.bionic.erestaurant.service.OrderService;

@Named
@Scope("request")
public class ReportsBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<ReportByDateResult> results = null;
	private Date dateFrom;
	private Date dateTo;
	
	//To remove later
	FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	
	@Inject
	private OrderService orderService;
	
	public ReportsBean() {
		this.dateFrom = Date.valueOf("2015-10-01");
		this.dateTo = Date.valueOf("2015-11-01");
	}

	public List<ReportByDateResult> getResults() {
		return results;
	}

	public void setResults(List<ReportByDateResult> results) {
		this.results = results;
	};
	
	public void getReport() {
		results = orderService.getOrderReportByTotal(this.dateFrom, this.dateTo);
		System.out.println(session.getAttribute("user").toString());
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

}

package com.bionic.erestaurant.bean;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bionic.erestaurant.core.reports.ReportByDateResult;
import com.bionic.erestaurant.service.OrderService;

@Named
@Scope("session")
public class ReportsBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<ReportByDateResult> results;
	private String dateFrom;
	private String dateTo;
	private static final Logger logger = Logger.getLogger(ReportsBean.class);

	
	//To remove later
	FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	
	@Autowired
	private OrderService orderService;
	
	public ReportsBean() {
		results = new ArrayList<ReportByDateResult>();
	}

	public List<ReportByDateResult> getResults() {
		return results;
	}

	public void setResults(List<ReportByDateResult> results) {
		this.results = results;
	};
	
	public void getReport() {
		logger.debug("Getting the report for the period: " + dateFrom + " - " + dateTo);
		results = orderService.getOrderReportByTotal(dateFrom, dateTo);
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

}

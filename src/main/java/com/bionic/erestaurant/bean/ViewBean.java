package com.bionic.erestaurant.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named
@Scope("request")
public class ViewBean implements Serializable {
	 private static final long serialVersionUID = 1L;
     private String page;

     @PostConstruct
     public void init() {
         page = "kitchenTemplate"; //  Default include.
     }

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
     
     
 }

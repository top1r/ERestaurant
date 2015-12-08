package com.bionic.erestaurant.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bionic.erestaurant.core.AddressHelper;
import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.service.AddressService;

@Named
@Scope("session")
public class AddressBean implements Serializable{
	private static final Logger logger = Logger.getLogger(AddressBean.class);
	
	private static final long serialVersionUID = 1L;
	private Address address;
	private int addressId;
	@Inject
	private AddressService addressService;
	
	public AddressBean() {
		address = new Address();
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		logger.log(Priority.INFO, "New Bean Created");
		session.setAttribute("address", address);
	}
	
	public void setById(){
		address = addressService.getById(addressId);
	}
	
	public String addressLater(){
		return "home";
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		logger.log(Priority.INFO, "New Address Created");
		this.address = address;
	}
	
	public List<String> getCountry(){
		if (logger.isDebugEnabled()){
			logger.log(org.apache.log4j.Level.DEBUG,"The list of countries is retrieved.");
		}
		return AddressHelper.getCountries();
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	
}

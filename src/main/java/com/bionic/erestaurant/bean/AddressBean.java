package com.bionic.erestaurant.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import com.bionic.erestaurant.core.AddressHelper;
import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.service.AddressService;

@Named
@Scope("session")
public class AddressBean implements Serializable{
	private static final long serialVersionUID = 1L;
	public Address address;
	@Inject
	private AddressService addressService;
	
	public AddressBean() {
		address = new Address();
	}
	
	public String addressLater(){
		return "home";
	}
	
	public String saveAddress() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		Users user = (Users)session.getAttribute("user");
		addressService.saveAddress(address, user);
		return "home";
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<String> getCountry(){
		return AddressHelper.getCountries();
	}
	
}

package com.bionic.erestaurant.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.exceptions.TransactionException;
import org.eclipse.persistence.jpa.rs.exceptions.PersistenceExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bionic.erestaurant.core.AddressHelper;
import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.entity.UsersRole;
import com.bionic.erestaurant.service.AddressService;
import com.bionic.erestaurant.service.UserService;

@Named
@Scope("session")
@SessionAttributes("address")

public class UsersBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Users user;
	private Address address;
	private List<Address> addressList;
	//used for rendered check
	private List<Users> userList;

	@Inject
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	public List<String> getCountry(){
		return AddressHelper.getCountries();
	}
	
	public UsersBean() {
		user = new Users();
		userList = new ArrayList<Users>();
		addressList = new ArrayList<Address>();
	}
	
	public Users getUser() {
		return user;
	}
	
	public void setUser(Users user) {
		this.user = user;
	}
	
	public String addressStep() {
		return "home";
	}
	
	public String saveUser() {
			
			System.out.println(user.getPassword());
			if (user.getPassword() == null){
				user.setPassword(user.generateHash(user.generateSalt(), user.generateSalt()));
			}
			

			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			Address address = (Address)session.getAttribute("address");
			System.out.println(address.getAddress1());
			addressList.add((Address)session.getAttribute("address"));
			address.setUser(user);
			user.setAddresses(addressList);
			userService.saveUser(user);
			session.setAttribute("user",user);
			Users user = (Users)session.getAttribute("user");
			if (user != null){
				//TODO remove redirect to the admin page
				if (user.getUserId() == -1) {
					return "reportList";
				} else {
					this.login();
					session.removeAttribute("address");
					return this.addressStep();
				}
			} else {
				return "login";
			}
	}
	
	public String login(){
		  FacesContext context = FacesContext.getCurrentInstance();
		  HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		  session.setAttribute("user", user);
		  System.out.println(user.toString());
		  userList.add(user);
		  return "reportList";
		  //session.setAttribute("email",  this.getUser().getEmail());
	}
	
	public String redirectLogout(){
		return "home.xhtml";
	}
	
	public String logout() throws IOException{
		  ExternalContext  context = FacesContext.getCurrentInstance().getExternalContext();
		  context.invalidateSession();
		  System.out.println(user.toString());
		  //userList.remove(user);
		  return "home.xhtml?faces-redirect=true";
		  //session.setAttribute("email",  this.getUser().getEmail());
	}
	
	public String validatePassword() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		//session.setAttribute("type", type);
		
		boolean valid = userService.validatePassword(user.getEmail(), user.getPassword());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		
		System.out.println(valid);

		if (valid) {
			user = userService.getByEmail(user.getEmail());
			return this.login();
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    		"Either the User ID or password entered is incorrect.", 
                    		"Enter the information again."));
            return null;
		}	

		//System.out.println(type);
		//return this.addressStep();		
	}
	/*
	public String validateUser() {
		if (userService.getByEmail(user.getEmail()).getUserId() != 0){
			FacesContext.getCurrentInstance().addMessage(
					null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    		"The user exists", 
                    		"Enter the information again."));
			return null;
		} else {
			
		}
	}
	*/

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}
}

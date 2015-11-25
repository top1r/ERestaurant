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
import com.bionic.erestaurant.entity.UsersRole.UserRoleEnum;
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
	private String userSearchEmail;
	private List<Users> userSearchList;
	private List<String> roleList;
	private List<String> adminRoleList;
	
	public List<String> getAdminRoleList() {
		return adminRoleList;
	}

	public void setAdminRoleList(List<String> adminRoleList) {
		this.adminRoleList = adminRoleList;
	}

	private boolean isStaff;
	
	private boolean isAdmin;
	private boolean isBusiness;
	private boolean isKitchen;
	private boolean isDelivery;

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public boolean isStaff() {
		return isStaff;
	}

	public void setStaff(boolean isStaff) {
		this.isStaff = isStaff;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isBusiness() {
		return isBusiness;
	}

	public void setBusiness(boolean isBusiness) {
		this.isBusiness = isBusiness;
	}

	public boolean isKitchen() {
		return isKitchen;
	}

	public void setKitchen(boolean isKitchen) {
		this.isKitchen = isKitchen;
	}

	public boolean isDelivery() {
		return isDelivery;
	}

	public void setDelivery(boolean isDelivery) {
		this.isDelivery = isDelivery;
	}

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
		return "cart";
	}
	
	public String homeRedirect() {
		return "home";
	}
	
	public void searchByEmail(){
		System.out.println(userSearchEmail);
	
		userSearchList = userService.searchByEmail(userSearchEmail);
		System.out.println(userSearchList.size());
	}

	public String saveUser() {
			List<UsersRole> rolelist = new ArrayList<UsersRole>();
			System.out.println(user.getPassword());
			if (user.getPassword() == null){
				UsersRole guest = new UsersRole();
				guest.setType(UserRoleEnum.GUEST.toString());
				rolelist.add(guest);
				guest.setUser(user);

				user.setPassword(user.generateHash(user.generateSalt(), user.generateSalt()));
			} else {
				UsersRole registered = new UsersRole();
				registered.setType(UserRoleEnum.REGISTERED.toString());
				registered.setUser(user);
				rolelist.add(registered);
			}

			user.setRoles(rolelist);

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
				if (this.isAdmin) {
					return "smc";
				} else {
					this.login();
					//session.removeAttribute("address");
					return this.addressStep();
				}
			} else {
				return "login";
			}
	}
	
	public String userRedirect(){
		return "users";
	}
	
	public String kitchenRedirect(){
		return "kitchen";
	}
	
	public String businessRedirect(){
		return "business";
	}
	
	public String deliveryRedirect(){
		return "delivery";
	}

	public String login(){
		  FacesContext context = FacesContext.getCurrentInstance();
		  HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		  session.setAttribute("user", user);
		  System.out.println(user.toString());
		  userList.add(user);
		  roleList = user.rolesToList();
		  //Some dirty code starts here. Need to get rid of it as soon as the idea available;
		  this.setStaff(
				  	 roleList.contains(UserRoleEnum.SYSADMIN.toString()) 
				  || roleList.contains(UserRoleEnum.BUSINESS.toString())
				  || roleList.contains(UserRoleEnum.KITCHEN.toString())
				  || roleList.contains(UserRoleEnum.DELIVERY.toString())
				  );
			this.setAdmin(roleList.contains(UserRoleEnum.SYSADMIN.toString()));
			this.setBusiness(roleList.contains(UserRoleEnum.BUSINESS.toString()));
			this.setKitchen(roleList.contains(UserRoleEnum.KITCHEN.toString()));
			this.setDelivery(roleList.contains(UserRoleEnum.DELIVERY.toString()));

		  
		  if (isStaff){
			  return "smc";
		  } else {
			  return "cart";
		  }
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

	public List<Users> getUserSearchList() {
		return userSearchList;
	}

	public void setUserSearchList(List<Users> userSearchList) {
		this.userSearchList = userSearchList;
	}

	public String getUserSearchEmail() {
		return userSearchEmail;
	}

	public void setUserSearchEmail(String userSearchEmail) {
		this.userSearchEmail = userSearchEmail;
	}
}

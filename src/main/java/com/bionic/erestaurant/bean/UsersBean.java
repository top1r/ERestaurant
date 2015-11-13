package com.bionic.erestaurant.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.exceptions.TransactionException;
import org.eclipse.persistence.jpa.rs.exceptions.PersistenceExceptionMapper;
import org.springframework.context.annotation.Scope;

import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.entity.UsersRole;
import com.bionic.erestaurant.service.AddressService;
import com.bionic.erestaurant.service.UserService;

@Named
@Scope("session")
public class UsersBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Users user;
	//used for rendered check
	private List<Users> userList;

	@Inject
	private UserService userService;
	
	@Inject
	private AddressService addressService;
	
	public UsersBean() {
		user = new Users();
		userList = new LinkedList<Users>();
	}
	
	public Users getUser() {
		return user;
	}
	
	public void setUser(Users user) {
		this.user = user;
	}
	
	public String addressStep() {
		return "addressTemplate";
	}
	
	public String saveUser() {
			userService.saveUser(user);
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			session.setAttribute("user",user);
			Users user = (Users)session.getAttribute("user");
			if (user != null){
				//TODO remove redirect to the admin page
				if (user.getUserId() == -1) {
					return "reportList";
				} else {
					this.login();
					return this.addressStep();
				}
			} else {
				return "signIn";
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
}

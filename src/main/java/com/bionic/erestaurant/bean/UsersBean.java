package com.bionic.erestaurant.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.exceptions.TransactionException;
import org.eclipse.persistence.jpa.rs.exceptions.PersistenceExceptionMapper;
import org.springframework.context.annotation.Scope;

import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.service.UserService;

@Named
@Scope("session")
public class UsersBean {
	public Users user;
	@Inject
	private UserService userService;
	
	public UsersBean() {
		user = new Users();
	}
	
	public Users getUser() {
		return user;
	}
	
	public void setUser(Users user) {
		this.user = user;
	}
	
	public String saveUser() {
			userService.saveUser(user);
			//TODO remove redirect to the test page
			return "reportList";
	}
}

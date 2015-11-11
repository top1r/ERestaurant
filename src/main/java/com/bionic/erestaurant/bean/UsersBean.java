package com.bionic.erestaurant.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.exceptions.TransactionException;
import org.eclipse.persistence.jpa.rs.exceptions.PersistenceExceptionMapper;
import org.springframework.context.annotation.Scope;

import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.service.UserService;

@Named
@Scope("session")
public class UsersBean implements Serializable{
	private static final long serialVersionUID = 1L;
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
	
	public void login(){
		  FacesContext context = FacesContext.getCurrentInstance();
		  HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		  session.setAttribute("user", user);
		  //session.setAttribute("email",  this.getUser().getEmail());
	}
	
	public String validatePassword() {
		boolean valid = userService.validatePassword(user.getEmail(), user.getPassword());
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		
		System.out.println(valid);

		if (valid) {
			user = userService.getByEmail(user.getEmail());
			this.login();
			return "reportList";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    		"Either the User ID or password entered is incorrect.", 
                    		"Enter the information again."));
            return null;
		}
	}
}

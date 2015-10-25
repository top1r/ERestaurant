package com.bionic.erestaurant.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bionic.erestaurant.entity.Orderitems;
import com.bionic.erestaurant.entity.Orders;
import com.bionic.erestaurant.entity.UsersRole;
import com.bionic.erestaurant.entity.UsersRole.UserRoleEnum;
import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.service.UserService;
import com.bionic.erestaurant.service.UserRoleService;

public class RegisterUserServiceImpl implements RegisterUserService{
	private List<UsersRole> roles = new ArrayList<UsersRole>();
	private Users user;
	
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	UserService userService = (UserService)context.getBean("userServiceImpl");
	UserRoleService userRoleService = (UserRoleService)context.getBean("userRoleServiceImpl");
	
	public void addRole(UsersRole role) {
		roles.add(role);
	}

	public void removeRole(UsersRole role) {
		if (roles.contains(role)) {
			roles.remove(role);		
		} else {
			//Logger needs to be added here
		}
	}
	
	public RegisterUserServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public void registerUser(String email, String firstname, String lastname, String password, List<UsersRole> roles) {
		this.roles = roles;
		if (roles != null){
			this.user = new Users(email, firstname, lastname, password);			
			
			for (UsersRole r: this.roles){
				r.setUser(this.user);
			}
		} else {
			this.roles = new ArrayList<UsersRole>();
			if (password != null){
				this.user = new Users(email, firstname, lastname, password);
				UsersRole registered = new UsersRole();
				registered.setType(UserRoleEnum.REGISTERED.toString());
				this.roles.add(registered);
				registered.setUser(user);
			} else {
				this.user = new Users(email, firstname, lastname);
				UsersRole guest = new UsersRole();
				guest.setType(UserRoleEnum.GUEST.toString());				
				this.roles.add(guest);
				guest.setUser(this.user);
				System.out.println(guest.getUser().getEmail());	
				//this.user.setRoles((List<UserRole>)this.roles);
				
				
			}
			
		}
		this.user.setRoles((List<UsersRole>)this.roles);
		userService.saveUser(this.user);
		
	}
	
}

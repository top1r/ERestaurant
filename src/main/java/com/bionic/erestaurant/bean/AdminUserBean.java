package com.bionic.erestaurant.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.entity.UsersRole;
import com.bionic.erestaurant.entity.UsersRole.UserRoleEnum;
import com.bionic.erestaurant.service.UserService;

@Named
@Scope("request")
public class AdminUserBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Users user;
	private List<String> adminRoleList;
	
	@Inject
	private UserService userService;
	
	public AdminUserBean() {
		user = new Users();
		adminRoleList = new ArrayList<String>();
	}
	
	public String saveUser() {
		List<UsersRole> rolelist = new ArrayList<UsersRole>();

		UsersRole registered = new UsersRole();
		registered.setType(UserRoleEnum.REGISTERED.toString());
		registered.setUser(user);
		rolelist.add(registered);

		for (String s: adminRoleList){
			UsersRole role = new UsersRole();
			System.out.println(s);
			role.setType(UserRoleEnum.valueOf(s).toString());
			role.setUser(user);
			rolelist.add(role);
			}
		user.setRoles(rolelist);
		userService.saveUser(user);
		return "smc2?new=true";
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<String> getAdminRoleList() {
		return adminRoleList;
	}

	public void setAdminRoleList(List<String> adminRoleList) {
		this.adminRoleList = adminRoleList;
	}
}

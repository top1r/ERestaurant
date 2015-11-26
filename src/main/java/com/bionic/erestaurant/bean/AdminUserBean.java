package com.bionic.erestaurant.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.entity.UsersRole;
import com.bionic.erestaurant.entity.UsersRole.UserRoleEnum;
import com.bionic.erestaurant.service.UserService;

@Named
@Scope("session")
public class AdminUserBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Users user;
	private List<String> adminRoleList;
	private List<String> availableRolesList;
	
	public List<String> getAvailableRolesList() {
		return availableRolesList;
	}

	public void setAvailableRolesList(List<String> availableRolesList) {
		this.availableRolesList = availableRolesList;
	}

	private String userSearchEmail;
	private List<Users> userSearchList;
	
	@Inject
	private UserService userService;
	
	public AdminUserBean() {
		user = new Users();
		userSearchEmail = "";
		adminRoleList = new ArrayList<String>();
		availableRolesList = new ArrayList<String>();
		availableRolesList.add("BUSINESS");
		availableRolesList.add("KITCHEN");
		availableRolesList.add("DELIVERY");
	}
	
	public void searchByEmail(){
		System.out.println(userSearchEmail);
	
		userSearchList = userService.searchByEmail(userSearchEmail);
		System.out.println(userSearchList.size());
	}
	
	public void setAdminUser(String id){
	    int n = Integer.valueOf(id);
	    user = userService.getById(n);
	    System.out.println(user.getRoles().size());
	}

	
	public String saveUser() {
		List<UsersRole> rolelist = new ArrayList<UsersRole>();

		adminRoleList.add(UserRoleEnum.REGISTERED.toString());
		adminRoleList.add(UserRoleEnum.GUEST.toString());


		for (String s: adminRoleList){
			UsersRole role = new UsersRole();
			System.out.println(s);
			role.setType(UserRoleEnum.valueOf(s).toString());
			role.setUser(user);
			rolelist.add(role);
		}

		
		user.setRoles(rolelist);
		userService.saveUser(user);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminUserBean", null);

		return "smc?faces-redirect=true";
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

	public String getUserSearchEmail() {
		return userSearchEmail;
	}

	public void setUserSearchEmail(String userSearchEmail) {
		this.userSearchEmail = userSearchEmail;
	}

	public List<Users> getUserSearchList() {
		return userSearchList;
	}

	public void setUserSearchList(List<Users> userSearchList) {
		this.userSearchList = userSearchList;
	}
}

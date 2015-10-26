package com.bionic.erestaurant.core;

import java.util.List;
import java.util.Set;

import com.bionic.erestaurant.entity.UsersRole;

public interface RegisterUserService {
	public void addRole(UsersRole role);
	public void removeRole(UsersRole role);
	public void registerUser(String email, String firstname, String lastname, String password, List<UsersRole> roles);
}

package com.bionic.erestaurant.dao;

import com.bionic.erestaurant.entity.UsersRole;
import com.bionic.erestaurant.entity.Users;

public interface UserDao {
	public Users getById(int id);
	public void createUser(Users user);
	public Users getByEmail(String email);
}

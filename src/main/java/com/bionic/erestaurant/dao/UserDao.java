package com.bionic.erestaurant.dao;

import com.bionic.erestaurant.entity.UsersRole;

import java.util.List;

import com.bionic.erestaurant.entity.Users;

public interface UserDao {
	public Users getById(int id);
	public void saveUser(Users user);
	public Users getByEmail(String email);
	public List<Users> searchByEmail(String email);
}

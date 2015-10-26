package com.bionic.erestaurant.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.dao.UserDao;
import com.bionic.erestaurant.entity.UsersRole;
import com.bionic.erestaurant.entity.Users;

@Named
public class UserServiceImpl implements UserService {
	@Inject
	private UserDao userDao;
	
	public Users getById (int id){
		return userDao.getById(id);
	}
	
	public Users getByEmail (String email){
		return userDao.getByEmail(email);
	}
	
	@Transactional
	public void saveUser(Users user){
		userDao.saveUser(user);
	}
}

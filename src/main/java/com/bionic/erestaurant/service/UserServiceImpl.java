package com.bionic.erestaurant.service;

import java.sql.SQLException;

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
		user.generateSecurePassword();
		userDao.saveUser(user);
	}
	
	public boolean validatePassword(String email, String password){
		Users user = userDao.getByEmail(email);
		System.out.println(user);
		if ((user != null) && (user.getPassword() != null)){
			if (user.getPassword().equals(user.generateHash(password, user.getSalt()))){
				return true;
			} else {
				System.out.println("here");
				return false;
			}
		} else {
			//TODO Add better logging
			System.out.println("Null user or password");
			return false;
		}
	}
}

package com.bionic.erestaurant.service;

import com.bionic.erestaurant.entity.UsersRole;
import com.bionic.erestaurant.entity.Users;

public interface UserService {
		public Users getById(int id);
		public Users getByEmail(String email);
		public void saveUser(Users user);
		public boolean validatePassword(String email, String hash);
}

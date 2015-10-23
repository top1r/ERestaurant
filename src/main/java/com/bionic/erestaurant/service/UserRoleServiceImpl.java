package com.bionic.erestaurant.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.bionic.erestaurant.dao.UserRoleDao;
import com.bionic.erestaurant.entity.UsersRole;

@Named
public class UserRoleServiceImpl implements UserRoleService {
	@Inject
	private UserRoleDao userRoleDao;
	public UsersRole getById (int id){
		return userRoleDao.getById(id);
	}
}

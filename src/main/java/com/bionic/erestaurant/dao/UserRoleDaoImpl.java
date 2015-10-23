package com.bionic.erestaurant.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bionic.erestaurant.entity.UsersRole;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	@PersistenceContext
	private EntityManager em;
	public UsersRole getById(int id){
		return em.find(UsersRole.class, id);
	};
}

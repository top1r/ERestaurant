package com.bionic.erestaurant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.erestaurant.entity.Users;

@Repository
public class UserDaoImpl implements UserDao{
	@PersistenceContext
	private EntityManager em;
	
	public Users getById(int id){
		return em.find(Users.class, id);
	}
	
	public Users getByEmail(String email){
			TypedQuery<Users> query = em.createQuery("SELECT u from Users u where u.email = :email", Users.class);
			try {
				return query.setParameter("email", email).getSingleResult();
			} catch (NoResultException n){
				return null;
			}
	}	
	
	public void saveUser(Users user){
		if (user.getUserId() == 0){
			em.persist(user);
		}
		else {
			em.merge(user);
		}
	}
}

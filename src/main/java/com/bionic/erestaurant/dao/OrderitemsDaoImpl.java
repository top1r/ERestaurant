package com.bionic.erestaurant.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bionic.erestaurant.entity.Orderitems;

@Repository
public class OrderitemsDaoImpl implements OrderitemsDao{
	@PersistenceContext
	private EntityManager em;
	
	public void saveOrderItem(Orderitems o){
		em.persist(o);
	}
	
	public Orderitems getOrderitemsById(int id){
		return em.find(Orderitems.class, id);
	}
	
}

package com.bionic.erestaurant.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bionic.erestaurant.entity.Orderitems;

@Repository
public class OrderitemsDaoImpl implements OrderitemsDao{
	@PersistenceContext
	private EntityManager em;
	
	public void createOrderItem(Orderitems o){
		em.persist(o);
	}
	
	public void saveOrderItem(Orderitems o){
		if (o.getOrderitemId() == 0){
			//Logger goes here
			System.out.println("Orderitem does not exist");
		} else {
			em.merge(o);
		}
	}
	
	public Orderitems getOrderitemsById(int id){
		return em.find(Orderitems.class, id);
	}
	
}

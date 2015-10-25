package com.bionic.erestaurant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.erestaurant.entity.Orderitems;
import com.bionic.erestaurant.entity.Orderitems.orderitemsStatus;

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
	
	public List<Orderitems> getKitchenPendingList(){
		String txt = "SELECT o from Orderitems o, Product p "
				+ "where o.product_id = p.product_id "
				+ "and o.status = :status "
				+ "and p.isKitchen = true";
		TypedQuery<Orderitems> query = em.createQuery(txt, Orderitems.class);
		return query.setParameter("status", orderitemsStatus.NEW.toString()).getResultList();
	}
	
	public Orderitems getOrderitemsById(int id){
		return em.find(Orderitems.class, id);
	}
	
}

package com.bionic.erestaurant.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.stereotype.Repository;

import com.bionic.erestaurant.bean.OrderBean;
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
			Logger.getLogger(OrderBean.class).log(Priority.ERROR, "Orderitem does not exist");
		} else {
			em.merge(o);
		}
	}
	
	public List<Orderitems> getKitchenPendingList(){
		String txt = "SELECT oi from Orderitems oi, Product p "
				+ "where oi.product_id = p.product_id "
				+ "and oi.status = :status "
				+ "and p.isKitchen = true "
				+ "order by oi.created asc";
		TypedQuery<Orderitems> query = em.createQuery(txt, Orderitems.class);
		return query.setParameter("status", orderitemsStatus.NEW.toString()).getResultList();
	}
	
	public Orderitems getOrderitemsById(int id){
		return em.find(Orderitems.class, id);
	}
	
}

package com.bionic.erestaurant.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Order;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.core.reports.ReportByDateResult;
import com.bionic.erestaurant.entity.Orders;

@Repository
public class OrderDaoImpl implements OrderDao{
	@PersistenceContext
	private EntityManager em;
	
	public Orders getOrderById(int id){
		return em.find(Orders.class, id);
	}
	
	public void createOrder(Orders order){
		em.persist(order);
	}
	
	public void saveOrder(Orders order){
		if (order.getOrderId() == 0){
			//Logging goes here
			System.out.println("Order doesn't exist in the context");
		} else {
			em.merge(order);
		}
	}
	
	public List<Orders> getOrderByUser(int user_id){
		String txt = "SELECT o FROM Orders o WHERE o.user_id = :userId";
		TypedQuery<Orders> query = em.createQuery(txt, Orders.class);
		return query.setParameter("userId", user_id).getResultList();
	}
	
	public List<ReportByDateResult> getOrderReportByTotal(Date dateFrom, Date dateTo){
		String rawQuery = "select new com.bionic.erestaurant.core.reports.ReportByDateResult(FUNCTION('DATE',oi.created), count(distinct oi.order), sum(oi.quantity * p.price)) "
				+ "from Orderitems oi, Product p "
				+ "where oi.product_id = p.product_id "
				+ "and FUNCTION('DATE',oi.created) between :dateFrom and :dateTo "
				+ "group by FUNCTION('DATE',oi.created)";
		TypedQuery<ReportByDateResult> query = em.createQuery(rawQuery, ReportByDateResult.class);
		return query.setParameter("dateFrom", dateFrom).setParameter("dateTo", dateTo).getResultList();
	}
	
}

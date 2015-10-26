package com.bionic.erestaurant.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.erestaurant.entity.Category;
import com.bionic.erestaurant.entity.Product;

@Repository
public class CategoryDaoImpl implements CategoryDao{
	@PersistenceContext
	private EntityManager em;
	public void addCategory(Category category){
		em.persist(category);
	}
	public List<Product> getProductsByCategory(Category category){
		return category.getProducts();
	}
	/*
	public List<String> getProductCount(){
		String query = "SELECT c.name FROM category c WHERE c.isonline = true"
		TypedQuery<>
	}
	*/
}

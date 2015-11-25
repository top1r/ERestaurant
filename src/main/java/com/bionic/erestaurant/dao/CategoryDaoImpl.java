package com.bionic.erestaurant.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.erestaurant.entity.Category;
import com.bionic.erestaurant.entity.Product;
import com.bionic.erestaurant.entity.Users;

@Repository
public class CategoryDaoImpl implements CategoryDao{
	@PersistenceContext
	private EntityManager em;
	public void saveCategory(Category category){
		if (category.getCategory_id() == 0){
			em.persist(category);
		} else {
			em.merge(category);
		}
		
	}
	public List<Product> getProductsByCategory(Category category){
		return category.getProducts();
	}
	
	public List<Category> getCategoriesByName(String name){
		//online status should be checked during iteration during the generation of the page
		String txt = "select c from Category c where c.name like :name";
		TypedQuery<Category> query = em.createQuery(txt, Category.class);
		return query
				.setParameter("name", "%" + name + "%")
				.getResultList();
	}
	
	public Category getCategoryByName(String name){
		TypedQuery<Category> query = em.createQuery("SELECT c from Category c where c.name = :name", Category.class);
		try {
			return query.setParameter("name", name).getSingleResult();
		} catch (NoResultException n){
			return null;
		}
	}
	
	public Category getById(int id){
		return em.find(Category.class, id);
	}
	
	/*
	public List<String> getProductCount(){
		String query = "SELECT c.name FROM category c WHERE c.isonline = true"
		TypedQuery<>
	}
	*/
}

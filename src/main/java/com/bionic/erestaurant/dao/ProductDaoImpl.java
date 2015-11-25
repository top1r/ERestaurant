package com.bionic.erestaurant.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.entity.Category;
import com.bionic.erestaurant.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao{
	@PersistenceContext
	private EntityManager em;
	
	public Product getProductById(int id){
		return em.find(Product.class, id);
	}
	
	public void deleteProduct(Product p){
		if (em.find(Product.class, p.getProductId()) == null){
			em.remove(p);
		} else {
			System.out.println("No product to remove");
		}
	}
	
	@Transactional
	public void saveProduct(Product p){
		if (p.getProductId() == 0){
			em.persist(p);
		} else {
			em.merge(p);
		}
		
	}
	
	public List<Product> getProductsByName(String name){
		//online status should be checked during iteration during the generation of the page
		String txt = "select p from Product p where FUNCTION('UPPER',p.name) like FUNCTION('UPPER',:name)";
		TypedQuery<Product> query = em.createQuery(txt, Product.class);
		return query
				.setParameter("name", "%" + name + "%")
				.getResultList();
	}
	
	public Map<Category, Integer> getCategoriesCount(List<Product> products){
		Map<Category, Integer> facet = new HashMap<Category,Integer>();
		if ((products != null) && (products.size() > 0)) {
			for (Product p: products){
				//List<Category> categories = p.getCategories(); 
				for (Category c: p.getCategories()){
					if (facet.keySet().contains(c)){
						facet.put(c, (Integer)facet.get(c) + 1);
					} else {
						facet.put(c, 1);
					}
				}
			}
			return facet;
		} else {
			//Logger to add here
			System.out.println("No results found");
			return null;
		}		
	}
	
	public Product getProductByName(String name){
		TypedQuery<Product> query = em.createQuery("SELECT p from Product p where p.name = :name", Product.class);
		try {
			return query.setParameter("name", name).getSingleResult();
		} catch (NoResultException n){
			return null;
		}
	}
}

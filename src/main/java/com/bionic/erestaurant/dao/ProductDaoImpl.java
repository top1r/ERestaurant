package com.bionic.erestaurant.dao;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bionic.erestaurant.entity.Category;
import com.bionic.erestaurant.entity.Product;

@Repository
public class ProductDaoImpl implements ProductDao{
	@PersistenceContext
	private EntityManager em;
	
	public Product getProductById(int id){
		return em.find(Product.class, id);
	}
	
	public void saveProduct(Product p){
		em.persist(p);
	}
	
	public List<Product> getProductsByName(String name){
		String txt = "select p from Product p where p.name like :name and p.isOnline = true";
		TypedQuery<Product> query = em.createQuery(txt, Product.class);
		return query
				.setParameter("name", "%" + name + "%")
				.getResultList();
	}
	
	public String getCategoriesCount(List<Product> products){
		Map<String, Integer> facet = new TreeMap<String,Integer>();
		if (products.size() > 0) {
			for (Product p: products){
				//List<Category> categories = p.getCategories(); 
				for (Category c: p.getCategories()){
					if (facet.keySet().contains(c.getName())){
						facet.put((String)c.getName(), (Integer)facet.get(c.getName()) + 1);
					} else {
						facet.put((String)c.getName(), 1);
					}
				}
			}
			return facet.toString();
		} else {
			//Logger to add here
			System.out.println("No results found");
			return null;
		}		
	}
}

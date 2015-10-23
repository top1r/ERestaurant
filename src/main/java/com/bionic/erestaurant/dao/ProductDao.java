package com.bionic.erestaurant.dao;

import java.util.List;

import com.bionic.erestaurant.entity.Product;

public interface ProductDao {
	public void saveProduct(Product p);
	public Product getProductById(int id);
	public List<Product> getProductsByName(String name);	
	public String getCategoriesCount(List<Product> products);
}

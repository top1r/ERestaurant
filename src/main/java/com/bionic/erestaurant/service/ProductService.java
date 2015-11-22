package com.bionic.erestaurant.service;

import java.util.List;
import java.util.Map;

import com.bionic.erestaurant.entity.Category;
import com.bionic.erestaurant.entity.Product;

public interface ProductService {
	public void saveProduct(Product p);
	public Product getProductById(int id);
	public List<Product> getProductsByName(String name);
	public Map<Category,Integer> getCategoriesCount(List<Product> products);
	public void deleteProduct (Product p);
}

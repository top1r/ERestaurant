package com.bionic.erestaurant.service;

import java.util.List;

import com.bionic.erestaurant.entity.Product;

public interface ProductService {
	public void saveProduct(Product p);
	public Product getProductById(int id);
	public List<Product> getProductsByName(String name);
	public String getCategoriesCount(List<Product> products);
}

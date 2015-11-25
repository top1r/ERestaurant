package com.bionic.erestaurant.dao;

import java.util.List;

import com.bionic.erestaurant.entity.Category;
import com.bionic.erestaurant.entity.Product;
import com.bionic.erestaurant.entity.Users;

public interface CategoryDao {
	public void saveCategory(Category category);
	public List<Product> getProductsByCategory(Category category);
	public List<Category> getCategoriesByName(String name);
	public Category getCategoryByName(String name);
	public Category getById(int id);
}

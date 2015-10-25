package com.bionic.erestaurant.dao;

import java.util.List;

import com.bionic.erestaurant.entity.Category;
import com.bionic.erestaurant.entity.Product;

public interface CategoryDao {
	public void saveCategory(Category category);
	public List<Product> getProductsByCategory(Category category);
	public List<Category> getCategoriesByName(String name);
}

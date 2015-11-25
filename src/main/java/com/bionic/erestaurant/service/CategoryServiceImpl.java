package com.bionic.erestaurant.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.dao.CategoryDao;
import com.bionic.erestaurant.entity.Category;
import com.bionic.erestaurant.entity.Product;

@Named
public class CategoryServiceImpl implements CategoryService{
	@Inject
	private CategoryDao categoryDao;
	
	public List<Product> getProductsByCategory(Category category){
		return categoryDao.getProductsByCategory(category);
	}
	
	public List<Category> getCategoriesByName(String name){
		return categoryDao.getCategoriesByName(name);
	}
	
	@Transactional
	public void saveCategory(Category category){
		categoryDao.saveCategory(category);
	}
	
	public Category getCategoryByName(String name){
		return categoryDao.getCategoryByName(name);
	}
}

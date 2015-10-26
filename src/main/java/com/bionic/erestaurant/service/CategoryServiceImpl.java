package com.bionic.erestaurant.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.dao.CategoryDao;
import com.bionic.erestaurant.entity.Category;

@Named
public class CategoryServiceImpl implements CategoryService{
	@Inject
	private CategoryDao categoryDao;
	
	@Transactional
	public void addCategory(Category category){
		categoryDao.addCategory(category);
	}
}

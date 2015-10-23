package com.bionic.erestaurant.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.bionic.erestaurant.dao.ProductDao;
import com.bionic.erestaurant.entity.Product;

@Named
public class ProductServiceImpl implements ProductService{
	@Inject
	private ProductDao productDao;
	
	public void saveProduct(Product p){
		productDao.saveProduct(p);
	}
	
	public Product getProductById(int id){
		return productDao.getProductById(id);
	}
	
	public List<Product> getProductsByName(String name){
		return productDao.getProductsByName(name);
	}
	
	public String getCategoriesCount(List<Product> products){
		return productDao
				.getCategoriesCount(products);
	}
}

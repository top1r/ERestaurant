package com.bionic.erestaurant.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

import org.apache.derby.impl.sql.catalog.SYSFOREIGNKEYSRowFactory;
import org.eclipse.persistence.exceptions.TransactionException;
import org.eclipse.persistence.jpa.rs.exceptions.PersistenceExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bionic.erestaurant.core.AddressHelper;
import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Category;
import com.bionic.erestaurant.entity.Product;
import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.entity.UsersRole;
import com.bionic.erestaurant.service.AddressService;
import com.bionic.erestaurant.service.CategoryService;
import com.bionic.erestaurant.service.ProductService;
import com.bionic.erestaurant.service.UserService;

@Named
@Scope("session")

public class ProductBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Product product;
	private Category category;
	private List<Product> productList;
	private List<Category> categoryList;
	private String searchTerm = "";
	private String categorySearchTerm = "";
	private String productSearchTerm = "";
	public List<String> getCategoryToAddList() {
		return categoryToAddList;
	}

	public void setCategoryToAddList(List<String> categoryToAddList) {
		this.categoryToAddList = categoryToAddList;
	}


	private List<String> categoryStringList;
	private List<String> categoryToAddList;

	public List<String> getCategoryStringList() {
		return categoryStringList;
	}

	public void setCategoryStringList(List<String> categoryStringList) {
		this.categoryStringList = categoryStringList;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}


	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	public ProductBean() {
		productList = new ArrayList<Product>();
		product = new Product();
		categoryList = new ArrayList<Category>();
		category = new Category();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String searchRedirect(){
		return "search";
	}
	
	public String searchCatRedirect(){
		return "searchcat";
	}
	
	public void setCategoryById(String id){
	    int n = Integer.valueOf(id);
	    category = categoryService.getById(n);
	}
	
	public void setProductById(String id){
	    int n = Integer.valueOf(id);
	    product = productService.getProductById(n);
	}
	
	public void getCategoriesByName(){
		categoryList = categoryService.getCategoriesByName(categorySearchTerm);
	}
	
	public List<Product> getOnlineProducts(){
		productList = new ArrayList<Product>();
		productList.addAll(productService.getProductsByName(searchTerm)
				.stream()
				.filter(p->p.isOnline())
				.collect(Collectors.toList()));
		return productList;
	}
	
	public void getProductsByName(){
		productList = new ArrayList<Product>();
		productList.addAll(productService.getProductsByName(productSearchTerm));
	}
	
	public List<Category> getAllCategories(){
		categoryList = new ArrayList<Category>();
		categoryList = categoryService.getCategoriesByName("");
		return categoryList;
		
	}
	
	public void categoryToString(){
		categoryStringList = new ArrayList<String>();
		this.getAllCategories();
		for (Category c: categoryList){
			categoryStringList.add(c.getName());
		}
	}
	
	public List<Category> getOnlineCategories(){
		categoryList = new ArrayList<Category>();
		categoryList = categoryService.getCategoriesByName("");
		for (Iterator<Category> iterator = categoryList.iterator();iterator.hasNext();){
			if (!iterator.next().isOnline()){
				iterator.remove();
			}
		}
		return categoryList;
		
	}
	
	public void saveCategory(){
		categoryService.saveCategory(category);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productBean", null);

	}
	
	public void saveProduct(){
		categoryList = new ArrayList<Category>();
		if (categoryToAddList != null){
			for (String s: categoryToAddList){
				category = categoryService.getCategoryByName(s);
				categoryList.add(category);
			}
			product.setCategories(categoryList);
		}
		productService.saveProduct(product);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productBean", null);
	}
	
	public List<Product> getProductsByCategory(){
		productList = new ArrayList<Product>();
		productList = category.getProducts();
		productList
			.stream()
			.filter(p->p.isOnline())
			.collect(Collectors.toList());
		return productList;
	}
	
	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getCategorySearchTerm() {
		return categorySearchTerm;
	}

	public void setCategorySearchTerm(String categorySearchTerm) {
		this.categorySearchTerm = categorySearchTerm;
	}

	public String getProductSearchTerm() {
		return productSearchTerm;
	}

	public void setProductSearchTerm(String productSearchTerm) {
		this.productSearchTerm = productSearchTerm;
	}
	

}

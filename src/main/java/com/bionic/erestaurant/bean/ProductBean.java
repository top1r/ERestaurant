package com.bionic.erestaurant.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
	
	public String getOnlineProducts(){
		productList = new ArrayList<Product>();
		productList.addAll(productService.getProductsByName(searchTerm)
				.stream()
				.filter(p->p.isOnline())
				.collect(Collectors.toList()));
		return "search";
	}
	
	public List<Category> getOnlineCategories(){
		categoryList = new ArrayList<Category>();
		categoryList.addAll(categoryService.getCategoriesByName("")
				.stream()
				.filter(c->c.isOnline())
				.collect(Collectors.toList()));
		System.out.println(categoryList.size());
		return categoryList;
		
	}
	
	public void createNewCategory(){
		
	}
	
	public String getProductsByCategory(){
		productList = new ArrayList<Product>();
		System.out.println(category.getName());
		productList = category.getProducts();
		for (Iterator<Product> iterator = productList.iterator();iterator.hasNext();){
			if (!iterator.next().isOnline()){
				iterator.remove();
			}
		}
		return "search";
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

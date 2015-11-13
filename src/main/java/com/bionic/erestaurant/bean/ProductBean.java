package com.bionic.erestaurant.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.exceptions.TransactionException;
import org.eclipse.persistence.jpa.rs.exceptions.PersistenceExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bionic.erestaurant.core.AddressHelper;
import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Product;
import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.entity.UsersRole;
import com.bionic.erestaurant.service.AddressService;
import com.bionic.erestaurant.service.ProductService;
import com.bionic.erestaurant.service.UserService;

@Named
@Scope("request")

public class ProductBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Product product;

	@Inject
	private ProductService productService;
	
	public ProductBean() {
		product = new Product();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public List<Product> getAllOnlineProducts(){
		List<Product> onlineProducts = new ArrayList<Product>();
		onlineProducts.addAll(productService.getProductsByName("")
				.stream()
				.filter(p->p.isOnline())
				.collect(Collectors.toList()));
		return onlineProducts;
	}
	

}

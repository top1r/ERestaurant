package com.bionic.erestaurant.core;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.bionic.erestaurant.bean.ProductBean;
import com.bionic.erestaurant.entity.Product;
import com.sun.istack.logging.Logger;

@XmlRootElement
public class ProductList {

	private List<Product> products;
	
	public ProductList(){
		products = new ArrayList<Product>();
	}
	
	@XmlElement(name = "product", type = com.bionic.erestaurant.entity.Product.class)
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}

package com.bionic.erestaurant.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int category_id;
	private String name;
	private boolean isOnline;
	private String image_url; 
	private Timestamp created;
	private Timestamp lastupdated;
	
	@ManyToMany
	@JoinTable(
			name = "ProductCategoryAssignement",
			joinColumns = {@JoinColumn(name = "category_id")},
			inverseJoinColumns = {@JoinColumn(name = "product_id")}
			)
	private List<Product> products;
	
	public Category() {};
	
	public Category(String name) {
		this.name = name;
		this.isOnline = false;
		this.image_url = null;
		this.created = Timestamp.valueOf(LocalDateTime.now());
		this.lastupdated = this.created;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isOnline() {
		return isOnline;
	}
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	public Timestamp getLastupdated() {
		return lastupdated;
	}
	public void setLastupdated(Timestamp lastupdated) {
		this.lastupdated = lastupdated;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}

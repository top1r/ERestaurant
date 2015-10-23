package com.bionic.erestaurant.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UsersRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int role_id;
	public enum UserRoleEnum {
		SYSTEMADMIN, KITCHEN, DELIVERY, BUSINESS, GUEST, REGISTERED
	}
	private String role;
	private Timestamp created = Timestamp.valueOf(LocalDateTime.now());
	private Timestamp lastupdated;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private Users user;

	public int getRole_id() {
		return role_id;
	}
	
	public UsersRole() {};
	
	public UsersRole(UserRoleEnum type) {
		this.role = type.toString();
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
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

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getType() {
		return role;
	}

	public void setType(String type) {
		this.role = type;
	}
	
}

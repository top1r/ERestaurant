package com.bionic.erestaurant.entity;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	private boolean status;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private String salt; 
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private List<UsersRole> roles;
	

	@OneToMany(mappedBy="user", cascade = CascadeType.PERSIST)
	private List<Address> addresses;

	
	public Users() {
		this.status = true;
	}

	public Users(String email, String firstname, String lastname, String password) {
		this.status = true;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.salt = generateSalt();
		this.password = generateHash(password, this.salt);
	}
	
	public Users(String email, String firstname, String lastname) {
		this.status = true;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String generateSalt(){
		SecureRandom random = new SecureRandom();
		random.setSeed(System.currentTimeMillis());
		byte[] byteArray = new byte[16];
		random.nextBytes(byteArray);
		String salt = new String(Base64.getEncoder().encode(byteArray),Charset.forName("UTF-8"));
		//System.out.println(x);
		return salt;
	}
	
	public String generateHash(String pass, String salt){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e){
			e.getMessage();
		}			
		String passString = pass.concat(salt);
		byte[] hashByte = md.digest(passString.getBytes());
		System.out.println(passString);
		StringBuffer hash = new StringBuffer();
		for(byte b : hashByte) {
	        hash.append(String.format("%02x", b));
	    }
		System.out.println(hash);
		return hash.toString();
	}	

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void generateSecurePassword() {
		this.salt = generateSalt();
		this.password = generateHash(this.password, this.salt);
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	};
	
	@Override
	public String toString(){
		return firstname + " " + lastname + ": " + email + " " + password + " - " + salt;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public List<UsersRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UsersRole> roles) {
		this.roles = roles;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}

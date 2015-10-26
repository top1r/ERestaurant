package com.bionic.erestaurant.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.dao.AddressDao;
import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Users;

@Named
public class AddressServiceImpl implements AddressService{
	@Inject
	private AddressDao addressDao; 
	
	@Transactional
	public void saveAddress(Address address, Users users){
		if (users.getUserId() == 0){
			//Logger goes here
			System.out.println("User is not set");
		} else {
			address.setUserId(users.getUserId());
			addressDao.saveAddress(address, users);
		}		
	}
	
	public List<Address> getAddressesByUser(Users user){
		return addressDao.getAddressesByUser(user);
	}
}

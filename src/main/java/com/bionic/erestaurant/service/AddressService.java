package com.bionic.erestaurant.service;

import java.util.ArrayList;
import java.util.List;

import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Users;

public interface AddressService {
	//public void saveAddress(Address address, Users user);
	public List<Address> getAddressesByUserId(int id);
	public Address getById(int id);
}

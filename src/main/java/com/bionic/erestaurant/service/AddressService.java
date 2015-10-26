package com.bionic.erestaurant.service;

import java.util.ArrayList;
import java.util.List;

import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Users;

public interface AddressService {
	public void addAddress(Address address, Users user);
	public List<Address> getAddressesByUser(Users user);
}

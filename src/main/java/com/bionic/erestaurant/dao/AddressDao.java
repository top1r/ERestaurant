package com.bionic.erestaurant.dao;

import java.util.ArrayList;
import java.util.List;

import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Users;

public interface AddressDao {
	public void addAddress(Address address, Users user);
	public List<Address> getAddressesByUser(Users user);
}

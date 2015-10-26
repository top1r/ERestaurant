package com.bionic.erestaurant.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Users;

@Repository
public class AddressDaoImpl implements AddressDao{
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void saveAddress (Address address, Users user){
		if (address.getAddressId() == 0){
			em.persist(address);
		} else {
			em.merge(address);
		}
		
	}
	
	public List<Address> getAddressesByUser(Users user){
		TypedQuery<Address> query = em.createQuery("SELECT a from Address a where a.user_id = :userId", Address.class);
		return query.setParameter("userId", user.getUserId()).getResultList();
	}
}

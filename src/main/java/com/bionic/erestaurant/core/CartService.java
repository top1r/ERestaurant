package com.bionic.erestaurant.core;

import com.bionic.erestaurant.entity.*;

public interface CartService {
	public void addProduct(Product product);
	public void removeProduct(Product product);
	public double getCartTotal();
	public void submit();
}

package com.bionic.erestaurant.core;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ProductWS {
	String getOnlineProducts();
}

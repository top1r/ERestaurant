package com.bionic.erestaurant.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bionic.erestaurant.entity.Product;

public class ClientProduct {
	@SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context =  new ClassPathXmlApplicationContext("client-beans.xml");
        ProductWS service = context.getBean(ProductWS.class);
        String result = service.getOnlineProducts();
        System.out.println(result);
    }

}


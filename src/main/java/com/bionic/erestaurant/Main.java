package com.bionic.erestaurant;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.management.relation.RoleStatus;

import org.apache.derby.tools.sysinfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bionic.erestaurant.core.CartService;
import com.bionic.erestaurant.core.CartServiceImpl;
import com.bionic.erestaurant.core.RegisterUserService;
import com.bionic.erestaurant.core.RegisterUserServiceImpl;
import com.bionic.erestaurant.core.reports.ReportByDateResult;
import com.bionic.erestaurant.entity.Address;
import com.bionic.erestaurant.entity.Category;
import com.bionic.erestaurant.entity.Orderitems;
import com.bionic.erestaurant.entity.Product;
import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.entity.UsersRole;
import com.bionic.erestaurant.entity.UsersRole.UserRoleEnum;
import com.bionic.erestaurant.service.AddressService;
import com.bionic.erestaurant.service.CategoryService;
import com.bionic.erestaurant.service.OrderService;
import com.bionic.erestaurant.service.OrderitemsService;
import com.bionic.erestaurant.service.ProductService;
import com.bionic.erestaurant.service.UserService;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		UserService userService = (UserService)context.getBean("userServiceImpl");
		OrderService orderService = (OrderService)context.getBean("orderServiceImpl");
		OrderitemsService orderitemsService = (OrderitemsService)context.getBean("orderitemsServiceImpl");

		AddressService addressService = (AddressService)context.getBean("addressServiceImpl");

		// TODO Auto-generated method stub
		/*
		UserService userService = (UserService)context.getBean("userServiceImpl");		
		System.out.println(userService.getById(-1).toString());
		
		Users user = new Users("u", true, "test2@test1.com", "Test1", "Test2", "12345678");
		//System.out.println(user.toaString());
		
		Address address1 = new Address("Metalistiv str, 22","Kiev","Kiev","Ukraine","02140");
		Address address2 = new Address("Metalistiv str, 23","Kiev","Kiev","Ukraine","02140");
		
		
		//this is a test create user
		//userService.createUser(user);
		 * 
		 */
		
		RegisterUserServiceImpl rs = new RegisterUserServiceImpl();
		UsersRole kitchen = new UsersRole(UserRoleEnum.KITCHEN);
		UsersRole delivery = new UsersRole(UserRoleEnum.DELIVERY);
		List<UsersRole> roles = new ArrayList<>();
		roles.add(kitchen);
		roles.add(delivery);
		
		//rs.registerUser("test8@erestaurant.bionic.com", "Test1", "Employee", "XXXXXX", roles);
		
		
		Users user = userService.getByEmail("test2@test1.com");
		Address address = addressService.getAddressesByUser(user).get(0);
		address.setAddress1("Test address for change");
		addressService.saveAddress(address, user);
		/*AddressService addressService = (AddressService)context.getBean("addressServiceImpl");
		System.out.println(addressService.getAddressesByUser(user));
		*/
		
		CartServiceImpl cart = new CartServiceImpl();
		ProductService productService = (ProductService)context.getBean("productServiceImpl");	
		Product product1 = productService.getProductById(1);
		Product product2 = productService.getProductById(2);
		System.out.println(product2.toString());
		cart.addProduct(product1);
		cart.addProduct(product2);
		cart.addProduct(product1);
		cart.submit();
		
		/*
		CategoryService categoryService = (CategoryService)context.getBean("categoryServiceImpl");
		Category sales = new Category("Super Sale");
		categoryService.addCategory(sales);
		*/
		/*
		ProductService productService = (ProductService)context.getBean("productServiceImpl");
		List<Product> products = productService.getProductsByName("Test");
		System.out.println(productService.getCategoriesCount(products));
		System.out.println(orderService.getOrderByUser
						  (userService.getByEmail("test2@test1.com").getUserId()));
		*/
		List<ReportByDateResult> results = orderService
				.getOrderReportByTotal(Timestamp.valueOf("2015-10-10 00:00:00.000") ,Timestamp.valueOf("2015-10-17 00:00:00.000"));
		for (ReportByDateResult r: results){
			System.out.println(r.toString());
		}
		
		List<Orderitems> oil = orderitemsService.getKitchenPendingList();
		for (Orderitems oi: oil){
			System.out.println(oi.getLastupdated().toString() + " " + productService.getProductById(oi.getProductId()).getName() + "\n");
		}
		
	}

}

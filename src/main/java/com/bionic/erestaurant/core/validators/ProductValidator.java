package com.bionic.erestaurant.core.validators;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bionic.erestaurant.entity.Users;
import com.bionic.erestaurant.service.CategoryService;
import com.bionic.erestaurant.service.ProductService;
import com.bionic.erestaurant.service.UserService;

@Named
@Scope("session")
@FacesValidator("productValidator")
public class ProductValidator implements Validator{
	

	private String messageString;

	public ProductValidator() {
	}
	
		
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    //TODO add better logging
		Application app = context.getApplication();
		ValueExpression expression = app.getExpressionFactory().createValueExpression(context.getELContext(), "#{productServiceImpl}", Object.class);
		ProductService productService = (ProductService)expression.getValue( context.getELContext() );
	    
	    if (productService.getProductByName(value.toString()) != null) {
	    	messageString = " Product exists";
		      FacesMessage msg =
		              new FacesMessage(messageString);
		      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		    
		      throw new ValidatorException(msg);
	    } 

	  }
}

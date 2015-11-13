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
import com.bionic.erestaurant.service.UserService;

@Named
@Scope("session")
@FacesValidator("emailValidator")
public class EmailValidator implements Validator{
	
	private Pattern pattern;
	private Matcher matcher;
	private String messageString;
	
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public EmailValidator() {
		pattern = pattern.compile(EMAIL_PATTERN);
	}
	
	
		
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    //TODO add better logging
		Application app = context.getApplication();
		ValueExpression expression = app.getExpressionFactory().createValueExpression(context.getELContext(), "#{userServiceImpl}", Object.class);
		UserService userService = (UserService)expression.getValue( context.getELContext() );
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
		System.out.println("SessionType: " + session.getAttribute("user") +'\n');
		System.out.println("Validating submitted email -- " + value.toString());
		Users user = (Users)session.getAttribute("user");
		
	    matcher = pattern.matcher(value.toString());
	    
	    if (!matcher.matches()) {
	    	messageString = " E-mail validation failed.";
		      FacesMessage msg =
		              new FacesMessage(messageString);
		      msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		    
		      throw new ValidatorException(msg);
	    } else if ((user != null) && (userService.getByEmail(value.toString())) != null) {
	    	user = userService.getByEmail(value.toString());
	    	if (user.getUserId() != 0) {
		    	messageString = " The user with an E-mail " + value.toString() + " exists in the System";
			    FacesMessage msg =
			              new FacesMessage(messageString);
			    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			    
			    throw new ValidatorException(msg);
	    	}
	    } 

	  }
}

package com.bionic.erestaurant.bean;

import java.io.Serializable;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

@Named("msgs")
@Scope("request")
public class MessageBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String firstNameMessage;
	private String lastNameMessage;
	private String passwordMessage;
	private String emailMessage;
	
	//Email validation

	

	public MessageBean(){   }

}

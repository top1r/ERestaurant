package com.bionic.erestaurant.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class AddressHelper {
	
	public static List<String> getCountries(){
		List<String> countries = new ArrayList<String>();
		String[] locales =  Locale.getISOCountries();
		for (String countryCode: locales) {
			Locale obj = new Locale("", countryCode);
			countries.add(obj.getDisplayCountry());
		}
		java.util.Collections.sort(countries);
		return countries;
	}
}

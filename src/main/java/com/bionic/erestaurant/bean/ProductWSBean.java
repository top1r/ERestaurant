package com.bionic.erestaurant.bean;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.bionic.erestaurant.core.ProductList;
import com.bionic.erestaurant.core.ProductWS;
import com.bionic.erestaurant.entity.Product;
import com.bionic.erestaurant.service.ProductService;

@WebService(endpointInterface = "com.bionic.erestaurant.core.ProductWS")
@Named
public class ProductWSBean implements ProductWS{
	private ProductList products = new ProductList();
	@Inject
	private ProductService productService;
	
	public ProductWSBean(){   }
	
	public String getOnlineProducts(){
		try{
			products.setProducts(productService.getProductsByName(""));
	        JAXBContext jc = JAXBContext.newInstance(com.bionic.erestaurant.core.ProductList.class);
	        Marshaller m = jc.createMarshaller();
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        StringWriter sw = new StringWriter();
	        //Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
	        m.marshal(products, sw);
	        String txt = sw.toString();
	        return txt;
		}
		catch(JAXBException e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ProductList getProducts() {
		return products;
	}

	public void setProducts(ProductList products) {
		this.products = products;
	}
}

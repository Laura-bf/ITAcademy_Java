package com.nivel2.model.domain;

import java.util.ArrayList;
import java.util.List;

public class Florist {
	
	private static int countId=1;
	private int id;
    private String name;
    private List<Product> products;

    public Florist(String name){
    	this.id = countId;
    	countId++;
        this.name = name;
        this.products = new ArrayList<Product>();
    }
    
    public int getId() {
    	return id;
    }
    

    public String getName() {
        return this.name;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public Product getProduct(int id) {
    	Product product = null;
    	for(Product p : products) {
    		if(p.getId()==id) {
    			product = p;
    		}
    	}
    	return product;
    }
    
    public void add(Product product) {
        this.products.add(product);
    }
    
	@Override
	public String toString() {
		return "ID=" + id + "\tNombre: " + name;
	}


   
}

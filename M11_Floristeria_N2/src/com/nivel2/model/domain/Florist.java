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

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void add(Product product) {
        this.products.add(product);
    }

	@Override
	public String toString() {
		return "ID=" + id + "\tNombre: " + name;
	}


   
}

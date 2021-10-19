package com.nivel1.model.persistence;

import com.nivel1.model.domain.Florist;
import com.nivel1.model.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class FloristRepository {

    private static FloristRepository floristRepository;
    private static List<Florist> floristList;

    private FloristRepository(){
        floristList = new ArrayList<Florist>();
    }

    public static FloristRepository instance() {
        if (floristList == null) {
            floristRepository = new FloristRepository();
        }
        return floristRepository;
    }
    
    public void add(Florist florist) {
    	floristList.add(florist);
    }
    
    public void add(Product product, int id) {
    	this.getFloristById(id).add(product);
    }
    
    public List<Product> getProducts(int activeId){
    	return this.getFloristById(activeId).getProducts();
    }
    
    public int getSizeFlorist() {
    	return floristList.size();
    }
    
    public List<Florist> getFlorisList(){
    	return floristList;
    }
    
    public Florist getFloristById(int id) {
    	return floristList.get(id-1);
    }
}

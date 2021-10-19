package com.nivel2.model.persistence;

import java.util.ArrayList;
import java.util.List;

import com.nivel2.model.domain.Florist;
import com.nivel2.model.domain.Product;

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
    
    public void remove(Product product, int id) {
    	this.getFloristById(id).getProducts().remove(product);
    }
    
    public Product getProductById(int activeFloristId, int productId) {
    	return this.getFloristById(activeFloristId).getProduct(productId);
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

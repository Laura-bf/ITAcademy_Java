package com.white_collar.nivel1.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.white_collar.nivel1.model.domain.Picture;
import com.white_collar.nivel1.model.domain.Shop;
import com.white_collar.nivel1.model.repositories.ShopRepository;

@Service
public class ShopService {
	
	@Autowired
	ShopRepository shopRepository;
	
	public void addShop(String name, int maxCapacity) {
		shopRepository.save(new Shop(name, maxCapacity));
	}
	
	public List<Shop> getAllShops(){
		return (List<Shop>) shopRepository.findAll();
	}
	
	public void addPicture(Integer id, String name, String author) {
		Shop shop = shopRepository.findById(id).get();
		shop.addPicture(new Picture(name, author));
		shopRepository.save(shop);
	}
	
	public void addPicture(Integer id, Picture picture) {
		Shop shop = shopRepository.findById(id).get();
		shop.addPicture(picture);
		shopRepository.save(shop);
	}
	
	public List<Picture> getAllPictures(Integer id){
		return shopRepository.findById(id).get().getPictures();
	}
	
	public void eraseAllPictures(Integer id) {
		Shop shop = shopRepository.findById(id).get();
		shop.deletePictures();
		shopRepository.save(shop);
	}

}

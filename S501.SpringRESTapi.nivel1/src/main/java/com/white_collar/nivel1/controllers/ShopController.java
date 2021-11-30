package com.white_collar.nivel1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.white_collar.nivel1.model.services.ShopService;

@RestController
@RequestMapping("/shops")
public class ShopController {

	@Autowired
	ShopService service;
	
	//crea una tienda nueva y la añade al repositorio
	@PostMapping
	public void createNewShop(@RequestParam("name") String name, @RequestParam("max") int maxCapacity) {
		service.addShop(name, maxCapacity);
	}
	
	//devuelve el listado de tiendas con su nombre y capacidad
	@GetMapping
	public ResponseEntity<?> listAllShops(){
		return ResponseEntity.ok(service.getAllShops());
	}
	
	//añade un nuevo cuadro (por su nombre y autor) al listado de la tienda
	//uso RequestParam y no RequestBody porque el cuadro se crea al añadirse a una tienda, en el post no tiene todavía id, ni precio ni fecha así que no hay Picture para usar como body
	@PostMapping("/{id}/pictures")
	public void addPictureToShop(@PathVariable("id") Integer id, @RequestParam("name") String name, @RequestParam("author") String author) {
		service.addPicture(id, name, author);
		
	}
	
	//lista los cuadros de una tienda seleccionada por id
	@GetMapping("/{id}/pictures")
	public ResponseEntity<?> listShopPictures(@PathVariable("id") Integer id){
		return ResponseEntity.ok(service.getAllPictures(id));
	}
	
	//elimina todos los cuadros de una tienda
	@DeleteMapping("/{id}/pictures")
	public void deleteShopPictures(@PathVariable("id") Integer id) {
		service.eraseAllPictures(id);
	}
	
}

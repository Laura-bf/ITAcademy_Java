package com.white_collar.nivel3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.white_collar.nivel3.model.services.ShopService;

@RestController
@RequestMapping("/shops")
public class ShopController {

	@Autowired
	ShopService service;

	// devuelve el listado de tiendas con su nombre y capacidad
	@GetMapping
	public ResponseEntity<?> listAllShops() {
		ResponseEntity<?> result = null;
		try {
			result = ResponseEntity.ok(service.getAllShops());
		} catch (Exception e) {
			result = ResponseEntity.internalServerError().body(e.getMessage());
		}
		return result;
	}

	// crea una tienda nueva y la añade al repositorio
	@PostMapping
	public ResponseEntity<?> createNewShop(@RequestParam("name") String name, @RequestParam("max") int maxCapacity) {
		ResponseEntity<?> result = null;
		try {
			service.addShop(name, maxCapacity);
			result = ResponseEntity.status(HttpStatus.CREATED).body(service.getAllShops());
		} catch (Exception e) {
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return result;
	}

	// lista los cuadros de una tienda seleccionada por id
	@GetMapping("/{id}/pictures")
	public ResponseEntity<?> listShopPictures(@PathVariable("id") Integer id) {
		ResponseEntity<?> result = null;
		try {
			result = ResponseEntity.ok(service.getAllPictures(id));
		} catch (Exception e) {
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR - no existe ninguna tienda con ID:"+id);
		}
		return result;
	}

	// añade un nuevo cuadro (por su nombre y autor) al listado de la tienda
	// uso RequestParam y no RequestBody porque el cuadro se crea al añadirse a una
	// tienda, en el post no tiene todavía id, ni precio ni fecha así que no hay
	// Picture para usar como body
	@PostMapping("/{id}/pictures")
	public ResponseEntity<?> addPictureToShop(@PathVariable("id") Integer id, @RequestParam("name") String name,
			@RequestParam(name = "author", required = false) String author) {
		ResponseEntity<?> result = null;
		if(author==null)
			author="";
		try {
			service.addPicture(id, name, author);
			result = ResponseEntity.status(HttpStatus.CREATED).body(service.getAllPictures(id));
		} catch (Exception e) {
			result = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return result;
	}

	// elimina todos los cuadros de una tienda
	@DeleteMapping("/{id}/pictures")
	public ResponseEntity<?> deleteShopPictures(@PathVariable("id") Integer id) {
		ResponseEntity<?> result = null;
		try {
			service.eraseAllPictures(id);
			result = ResponseEntity.status(HttpStatus.OK).body(service.getAllPictures(id));
		} catch (Exception e) {
			result = ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR - no existe ninguna tienda con ID:"+id);
		}
		return result;
		
	}

}

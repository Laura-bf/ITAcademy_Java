package com.nivel1.controller.floristMenu;

import java.util.stream.Collectors;

import com.nivel1.controller.Controller;
import com.nivel1.model.domain.ActiveFlorist;
import com.nivel1.model.persistence.FloristRepository;
import com.nivel1.view.ShowInfoWindow;
import com.nivel1.view.utils.Session;

public class ShowProductsController extends Controller{
	
	FloristRepository floristRepository;
	
	public ShowProductsController(Session session) {
		super(session);
		this.floristRepository = FloristRepository.instance();
	}
	
	public void control() {
		int id = ActiveFlorist.instance().getId();
		ShowInfoWindow.showInfo(floristRepository.getProducts(id)
				.stream().map(l -> l.toString()).collect(Collectors.toList()));
	}

}

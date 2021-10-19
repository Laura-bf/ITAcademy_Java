package com.nivel2.controller.floristMenu;

import java.util.stream.Collectors;

import com.nivel2.controller.Controller;
import com.nivel2.model.domain.ActiveFlorist;
import com.nivel2.model.persistence.FloristRepository;
import com.nivel2.view.ShowInfoWindow;
import com.nivel2.view.utils.Session;

public class ChooseStockController extends Controller{
	
	FloristRepository floristRepository;
	
	public ChooseStockController(Session session) {
		super(session);
		this.floristRepository = FloristRepository.instance();
	}
	
	public void control() {
		int id = ActiveFlorist.instance().getId();
		ShowInfoWindow.showInfo(floristRepository.getProducts(id)
				.stream().map(l -> l.toString()).collect(Collectors.toList()));
	}

}

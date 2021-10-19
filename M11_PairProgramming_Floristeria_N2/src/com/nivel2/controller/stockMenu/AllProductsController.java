package com.nivel2.controller.stockMenu;

import com.nivel2.controller.Controller;
import com.nivel2.model.domain.ActiveFlorist;
import com.nivel2.model.persistence.FloristRepository;
import com.nivel2.view.ShowInfoWindow;
import com.nivel2.view.utils.Session;
import java.util.stream.Collectors;

public class AllProductsController extends Controller {

	FloristRepository floristRepository;
	
	public AllProductsController(Session session) {
		super(session);
		this.floristRepository = FloristRepository.instance();
	}
	
	public void control() {
		int id = ActiveFlorist.instance().getId();
		ShowInfoWindow.showInfoStocks("PRODUCTOS", floristRepository.getProducts(id)
				.stream().map(l -> l.toString()).collect(Collectors.toList()));
	}
}

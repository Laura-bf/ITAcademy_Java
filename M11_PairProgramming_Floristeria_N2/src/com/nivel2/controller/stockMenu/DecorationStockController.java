package com.nivel2.controller.stockMenu;

import java.util.stream.Collectors;

import com.nivel2.controller.Controller;
import com.nivel2.model.domain.ActiveFlorist;
import com.nivel2.model.domain.Decoration;
import com.nivel2.model.persistence.FloristRepository;
import com.nivel2.view.ShowInfoWindow;
import com.nivel2.view.utils.Session;

public class DecorationStockController extends Controller {

	FloristRepository floristRepository;

	public DecorationStockController(Session session) {
		super(session);
		this.floristRepository = FloristRepository.instance();
	}

	public void control() {
		int id = ActiveFlorist.instance().getId();
		ShowInfoWindow.showInfoStocks("DECORACIONES", floristRepository.getProducts(id)
				.stream().filter(s -> s instanceof Decoration).map(l -> l.toString()).collect(Collectors.toList()), this.getValueStock());
	}
	private double getValueStock() {
		int id = ActiveFlorist.instance().getId();
		return this.floristRepository.getProducts(id)
				.stream().filter(d -> d instanceof Decoration).map(d -> d.getPrice()).mapToDouble(Double::doubleValue).sum();
		
	}
}

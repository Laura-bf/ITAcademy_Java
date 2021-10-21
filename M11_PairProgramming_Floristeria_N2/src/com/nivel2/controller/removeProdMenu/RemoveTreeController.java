package com.nivel2.controller.removeProdMenu;

import java.util.List;
import java.util.stream.Collectors;

import com.nivel2.controller.Controller;
import com.nivel2.model.domain.ActiveFlorist;
import com.nivel2.model.domain.Product;
import com.nivel2.model.domain.Tree;
import com.nivel2.model.persistence.FloristRepository;
import com.nivel2.view.MessageView;
import com.nivel2.view.ReadInfoWindow;
import com.nivel2.view.ShowInfoWindow;
import com.nivel2.view.utils.Session;

public class RemoveTreeController extends Controller{

	FloristRepository floristRepository;
	
	public RemoveTreeController(Session session) {
		super(session);
		this.floristRepository = FloristRepository.instance();
	}

	public void control() {
		int activeFloristId = ActiveFlorist.instance().getId();
		List<String> treeStock = floristRepository.getProducts(activeFloristId).stream()
				.filter(s -> s instanceof Tree).map(l -> l.toString()).collect(Collectors.toList());
		ShowInfoWindow.showInfo(treeStock);
		if (treeStock.isEmpty()) {
			ShowInfoWindow.showInfo("NO HAY PRODUCTOS PARA RETIRAR DE ESTE STOCK");
		} else {
			Product product = floristRepository.getProductById(activeFloristId, this.getProdId());
			if (product instanceof Tree) {
				this.floristRepository.remove(product, activeFloristId);
				ShowInfoWindow.showInfo("ARBOL ELIMINADO");
			}
		}
	}
	private int getProdId() {
		return ReadInfoWindow.readIdProduct(MessageView.CHOOSE_PRODUCT, this.getIdList());
	}
	
	private List<Integer> getIdList() {
		int activeFloristId = ActiveFlorist.instance().getId();
		return this.floristRepository.getProducts(activeFloristId).stream().filter(s-> s instanceof Tree).map(s -> s.getId()).collect(Collectors.toList());
	}
}


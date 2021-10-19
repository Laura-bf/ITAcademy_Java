package com.nivel1.controller.floristMenu;

import com.nivel1.controller.Controller;
import com.nivel1.model.domain.*;
import com.nivel1.model.persistence.FloristRepository;
import com.nivel1.view.MessageView;
import com.nivel1.view.ReadInfoWindow;
import com.nivel1.view.ShowInfoWindow;
import com.nivel1.view.utils.Session;

public class CreateFlowerController extends Controller {
	private FloristRepository floristRep;

	public CreateFlowerController(Session session) {
		super(session);
		this.floristRep = FloristRepository.instance();
	}

	public void control() {
		Flower flower = this.createFlower();
		if (flower != null){
			this.floristRep.add(flower, ActiveFlorist.instance().getId());
			ShowInfoWindow.showInfo("FLOR AÑADIDA AL STOCK");
		}
	}

	private Flower createFlower() {
		Flower flower = null;
		String name = ReadInfoWindow.readString(MessageView.NAME_PRODUCT);
		double price = 0;
		String color = "";
		if (name != null){
			price = ReadInfoWindow.readDouble(MessageView.PRICE_PRODUCT);
			if (price != 0){
				color = ReadInfoWindow.readString(MessageView.COLOR_FLOWER);
				if (color != null){
					flower = new Flower(name, price, color);
				}
			}
		}
		return flower;
	}
}

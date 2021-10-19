package com.nivel1.controller.floristMenu;

import com.nivel1.controller.Controller;
import com.nivel1.model.domain.ActiveFlorist;
import com.nivel1.model.domain.Tree;
import com.nivel1.model.persistence.FloristRepository;
import com.nivel1.view.MessageView;
import com.nivel1.view.ReadInfoWindow;
import com.nivel1.view.ShowInfoWindow;
import com.nivel1.view.utils.Session;

public class CreateTreeController extends Controller {
	
	private FloristRepository floristRep;
	
	
	public CreateTreeController(Session session) {
		super(session);
		this.floristRep = FloristRepository.instance();
	}
	
	public void control() {
		Tree tree = this.createTree();
		if (tree != null){
			this.floristRep.add(tree, ActiveFlorist.instance().getId());
			ShowInfoWindow.showInfo("ARBOL AÑADIDO AL STOCK");
		}
	}
	
	private Tree createTree() {
		Tree tree = null;
		String name = ReadInfoWindow.readString(MessageView.NAME_PRODUCT);
		double price = 0;
		double height = 0;
		if (name != null){
			price = ReadInfoWindow.readDouble(MessageView.PRICE_PRODUCT);
			if (price != 0){
				height = ReadInfoWindow.readDouble(MessageView.HEIGHT_TREE);
				if (height != 0){
					tree = new Tree(name, price, height);
				}
			}
		}
		return tree;
	}
	
}

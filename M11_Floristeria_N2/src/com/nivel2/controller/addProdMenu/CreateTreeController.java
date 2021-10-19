package com.nivel2.controller.addProdMenu;

import com.nivel2.controller.Controller;
import com.nivel2.model.domain.ActiveFlorist;
import com.nivel2.model.domain.Tree;
import com.nivel2.model.persistence.FloristRepository;
import com.nivel2.view.MessageView;
import com.nivel2.view.ReadInfoWindow;
import com.nivel2.view.ShowInfoWindow;
import com.nivel2.view.utils.Session;

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

package com.nivel2.controller.floristMenu;

import com.nivel2.controller.Controller;
import com.nivel2.view.utils.Session;

public class ChooseStockController extends Controller{
	
	public ChooseStockController(Session session) {
		super(session);
	}
	
	public void control() {
		this.session.stockMenu();
	}

}

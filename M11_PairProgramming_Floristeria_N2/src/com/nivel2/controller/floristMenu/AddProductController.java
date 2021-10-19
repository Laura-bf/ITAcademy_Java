package com.nivel2.controller.floristMenu;

import com.nivel2.controller.Controller;
import com.nivel2.view.utils.Session;


public class AddProductController extends Controller {
	
	public AddProductController(Session session) {
		super(session);
	}
	
	public void control() {
		this.session.addMenu();
	}
	
}

package com.nivel2.controller;

import com.nivel2.view.utils.Session;

public class ExitToFloristMenuController extends Controller {

	public ExitToFloristMenuController(Session session) {
		super(session);
	}
	
	public void control() {
		this.session.floristMenu();
	}
	
}

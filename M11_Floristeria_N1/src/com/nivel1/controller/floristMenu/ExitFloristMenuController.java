package com.nivel1.controller.floristMenu;

import com.nivel1.controller.Controller;
import com.nivel1.view.ShowInfoWindow;
import com.nivel1.view.utils.Session;

public class ExitFloristMenuController extends Controller {
	
	public ExitFloristMenuController(Session session) {
		super(session);
		
	}
	
	public void control() {
		ShowInfoWindow.showInfo("VOLVIENDO AL MENU PRINCIPAL...");
		this.session.previousMenu();
	}

}

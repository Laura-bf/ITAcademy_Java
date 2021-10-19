package com.nivel2.controller.floristMenu;

import com.nivel2.controller.Controller;
import com.nivel2.view.ShowInfoWindow;
import com.nivel2.view.utils.Session;

public class ExitFloristMenuController extends Controller {
	
	public ExitFloristMenuController(Session session) {
		super(session);
		
	}
	
	public void control() {
		ShowInfoWindow.showInfo("VOLVIENDO AL MENU PRINCIPAL...");
		this.session.mainMenu();
	}

}

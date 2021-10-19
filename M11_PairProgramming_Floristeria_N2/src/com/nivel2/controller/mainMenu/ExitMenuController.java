package com.nivel2.controller.mainMenu;

import com.nivel2.controller.Controller;
import com.nivel2.view.ShowInfoWindow;
import com.nivel2.view.utils.Session;

public class ExitMenuController extends Controller{
	
	public ExitMenuController(Session session) {
		super(session);
	}
	
	public void control() {
		ShowInfoWindow.showInfo("SALIENDO DE LA APP...");
		this.session.exit();
	}
}

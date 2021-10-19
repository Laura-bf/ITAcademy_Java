package com.nivel1.controller.mainMenu;

import com.nivel1.controller.Controller;
import com.nivel1.view.ShowInfoWindow;
import com.nivel1.view.utils.Session;

public class ExitMenuController extends Controller{
	
	public ExitMenuController(Session session) {
		super(session);
	}
	
	public void control() {
		ShowInfoWindow.showInfo("SALIENDO DE LA APP...");
		this.session.exit();
	}
}

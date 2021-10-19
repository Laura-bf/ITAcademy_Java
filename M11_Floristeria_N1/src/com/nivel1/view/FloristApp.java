package com.nivel1.view;

import com.nivel1.view.utils.Session;
import com.nivel1.view.utils.StateValue;

import java.util.HashMap;
import java.util.Map;

import com.nivel1.controller.*;
import com.nivel1.controller.floristMenu.FloristMenuController;
import com.nivel1.controller.mainMenu.MainMenuController;

public class FloristApp {
	
	private Session session;
	private Map<StateValue, Controller> controllers;
	
	public FloristApp() {
		this.session = new Session();
		this.controllers = new HashMap<StateValue, Controller>();
		this.controllers.put(StateValue.MAIN_MENU, new MainMenuController(this.session));
		this.controllers.put(StateValue.FLORIST_MENU, new FloristMenuController(this.session));
		this.controllers.put(StateValue.EXIT_MENU, null);
	}
	
	private void execute() {
		Controller controller;
		do {
			controller = this.controllers.get(this.session.getStateValue());
			if(controller!=null) {
				controller.control();
			}
		}while(controller!=null);
	}
	
	
    public static void main(String[] args) {
		
    	new FloristApp().execute();
    	
    }
}

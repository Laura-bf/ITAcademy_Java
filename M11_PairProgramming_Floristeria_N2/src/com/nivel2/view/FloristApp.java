package com.nivel2.view;

import com.nivel2.controller.*;
import com.nivel2.controller.addProdMenu.AddMenuController;
import com.nivel2.controller.floristMenu.FloristMenuController;
import com.nivel2.controller.mainMenu.MainMenuController;
import com.nivel2.controller.removeProdMenu.RemoveMenuController;
import com.nivel2.controller.stockMenu.StockMenuController;
import com.nivel2.view.utils.Session;
import com.nivel2.view.utils.StateValue;

import java.util.HashMap;
import java.util.Map;

public class FloristApp {
	
	private Session session;
	private Map<StateValue, Controller> controllers;
	
	public FloristApp() {
		this.session = new Session();
		this.controllers = new HashMap<StateValue, Controller>();
		this.controllers.put(StateValue.MAIN_MENU, new MainMenuController(this.session));
		this.controllers.put(StateValue.FLORIST_MENU, new FloristMenuController(this.session));
		this.controllers.put(StateValue.ADD_MENU, new AddMenuController(this.session));
		this.controllers.put(StateValue.REMOVE_MENU, new RemoveMenuController(this.session));
		this.controllers.put(StateValue.STOCK_MENU, new StockMenuController(this.session));
		this.controllers.put(StateValue.EXIT_APP, null);
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

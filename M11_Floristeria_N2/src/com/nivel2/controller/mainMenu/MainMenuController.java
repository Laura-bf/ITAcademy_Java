package com.nivel2.controller.mainMenu;

import java.util.HashMap;
import java.util.Map;

import com.nivel2.controller.Controller;
import com.nivel2.view.Menu;
import com.nivel2.view.mainMenu.*;
import com.nivel2.view.utils.Command;
import com.nivel2.view.utils.Session;

public class MainMenuController extends Controller{
	
	private Map<Command, Controller> controllers;
	private CreateFloristController createFloristController;
	private CreateFloristCommand createFloristCommand;
	private ChooseFloristController chooseFloristController;
	private ChooseFloristCommand chooseFloristCommand;
	private ExitMenuController exitMenuController;
	private ExitMenuCommand exitMenuCommand;
	private Menu menu;
	
	public MainMenuController(Session session) {
		super(session);
		this.controllers = new HashMap<Command, Controller>();
		this.createFloristController = new CreateFloristController(this.session);
		this.createFloristCommand = new CreateFloristCommand();
		this.chooseFloristController = new ChooseFloristController(this.session);
		this.chooseFloristCommand = new ChooseFloristCommand("SELECCIONAR FLORISTERIA");
		this.exitMenuController = new ExitMenuController(this.session);
		this.exitMenuCommand = new ExitMenuCommand();

		this.controllers.put(this.createFloristCommand, this.createFloristController);
		this.controllers.put(this.chooseFloristCommand, this.chooseFloristController);
		this.controllers.put(this.exitMenuCommand, this.exitMenuController);
		
		this.menu = new Menu(this.controllers.keySet());
	}

	public void control() {
		this.createFloristCommand.setActive(true);
		this.chooseFloristCommand.setActive(!this.chooseFloristController.isFloristListEmpty());
		this.exitMenuCommand.setActive(true);
		this.controllers.get(this.menu.execute()).control();
		
	}
	
}

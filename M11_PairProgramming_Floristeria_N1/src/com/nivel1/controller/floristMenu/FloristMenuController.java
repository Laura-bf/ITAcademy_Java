package com.nivel1.controller.floristMenu;

import java.util.HashMap;
import java.util.Map;

import com.nivel1.controller.Controller;
import com.nivel1.controller.mainMenu.ChooseFloristController;
import com.nivel1.view.Menu;
import com.nivel1.view.floristMenu.*;
import com.nivel1.view.mainMenu.ChooseFloristCommand;
import com.nivel1.view.utils.Command;
import com.nivel1.view.utils.Session;

public class FloristMenuController extends Controller{
	private CreateTreeController createTreeController;
	private CreateTreeCommand createTreeCommand;
	private CreateFlowerController createFlowerController;
	private CreateFlowerCommand createFlowerCommand;
	private CreateDecorationController createDecorationController;
	private CreateDecorationCommand createDecorationCommand;
	private ShowProductsController showProductsController;
	private ShowProductsCommand showProductsCommand;
	private ChooseFloristController chooseFloristController;
	private ChooseFloristCommand chooseFloristCommand;
	private ExitFloristMenuController exitFloristMenuController;
	private ExitFloristMenuCommand exitFloristMenuCommand;
	
	private Map<Command, Controller> controllers;
	private Menu menu;
	
	public FloristMenuController(Session session) {
		super(session);
		this.controllers = new HashMap<Command, Controller>();
		this.createTreeController = new CreateTreeController(this.session);
		this.createTreeCommand = new CreateTreeCommand();
		this.createFlowerController = new CreateFlowerController(this.session);
		this.createFlowerCommand = new CreateFlowerCommand();
		this.createDecorationController = new CreateDecorationController(this.session);
		this.createDecorationCommand = new CreateDecorationCommand();
		
		this.showProductsController = new ShowProductsController(this.session);
		this.showProductsCommand = new ShowProductsCommand();
		
		this.chooseFloristController = new ChooseFloristController(this.session);
		this.chooseFloristCommand = new ChooseFloristCommand("ELEGIR OTRA FLORISTERIA");
		this.exitFloristMenuController = new ExitFloristMenuController(this.session);
		this.exitFloristMenuCommand = new ExitFloristMenuCommand();
		
		this.controllers.put(createTreeCommand, createTreeController);
		this.controllers.put(createFlowerCommand, createFlowerController);
		this.controllers.put(createDecorationCommand, createDecorationController);
		this.controllers.put(chooseFloristCommand, chooseFloristController);
		this.controllers.put(exitFloristMenuCommand, exitFloristMenuController);
		this.controllers.put(showProductsCommand, showProductsController);
		
		this.menu = new Menu(this.controllers.keySet());
	}
	
	
	
	public void control() {
		this.createTreeCommand.setActive(true);
		this.createFlowerCommand.setActive(true);
		this.createDecorationCommand.setActive(true);
		this.chooseFloristCommand.setActive(true);
		this.showProductsCommand.setActive(true);
		this.exitFloristMenuCommand.setActive(true);
		
		this.controllers.get(this.menu.execute()).control();
		
	}
}

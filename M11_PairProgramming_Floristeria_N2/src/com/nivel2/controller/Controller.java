package com.nivel2.controller;

import com.nivel2.view.utils.Session;

public abstract class Controller {
	
	protected Session session;
	
	public Controller(Session session) {
		this.session = session;
	}
	
	public abstract void control();
}

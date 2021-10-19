package com.nivel2.view.utils;

public class Session {

	private StateValue stateValue;
	
	public Session() {
		this.stateValue = StateValue.MAIN_MENU;
	}
	
	public StateValue getStateValue() {
		return this.stateValue;
	}
	
	public void setStateValue(StateValue stateValue) {
		this.stateValue = stateValue;
	}
	
	public void nextMenu() {
		 this.stateValue = StateValue.values()[this.stateValue.ordinal()+1];
	}
	
	public void previousMenu() {
		 this.stateValue = StateValue.values()[this.stateValue.ordinal()-1];
	}
	public void floristMenu() {
		this.stateValue = StateValue.FLORIST_MENU;
	}
	
	 public void exit() {
	        this.stateValue = StateValue.EXIT_MENU;
	}
}

package com.nivel1.model.domain;

public class ActiveFlorist {
	private static Florist activeFlorist;
	
	public static Florist instance() {
		return activeFlorist;
	}
	
	private ActiveFlorist() {
	}
	
	public static void setActiveFlorist(Florist florist) {
		activeFlorist = florist;
	}
}

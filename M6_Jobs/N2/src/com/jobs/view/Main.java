package com.jobs.view;

import com.jobs.application.JobsController;


public class Main {

	private static JobsController controller=new JobsController();
	
	public static void main(String[] args) throws Exception {
		
		controller.createBoss("Pepe Boss", "Direcci�n molona", "666666666", 9000.0);
//		controller.createBoss("Pepe Boss", "Direcci�n molona", "666666666", 5000.0);
		controller.createManager("Pedro Manager", "Direcci�n molona 2", "665266666", 4000.0);
//		controller.createManager("Pedro Manager", "Direcci�n molona 2", "665266666", 1000.0);
		controller.createSenior("Laura Employee", "Direcci�n molona 3", "625266666", 3000.0);
//		controller.createSenior("Laura Employee", "Direcci�n molona 3", "625266666", 1000.0);
		controller.createMid("Peter Employee", "Direcci�n molona 3", "625266666", 2500.0);
//		controller.createMid("Peter Employee", "Direcci�n molona 3", "625266666", 3000.0);
		controller.createJunior("John Employee", "Direcci�n molona 3", "625266666", 1500.0);
//		controller.createJunior("John Employee", "Direcci�n molona 3", "625266666", 900.0);
		controller.createVolunteer("Juan Volunteer", "Direcci�n molona 4", "614266666", "No cobra");
		
		
		
		controller.payAllEmployeers();
		
		String allEmployees=controller.getAllEmployees();
		
		System.out.println("EMPLOYEES:\n" + allEmployees + " \n");
		
	}

}

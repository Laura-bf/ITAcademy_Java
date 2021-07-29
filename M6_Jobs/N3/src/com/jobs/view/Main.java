package com.jobs.view;

import com.jobs.application.JobsController;

public class Main {

	private static JobsController controller=new JobsController();
	
	public static void main(String[] args) throws Exception {
		//para comprobar validaciones salario boss
//		controller.createBoss("Pepe Boss", "Direcci�n molona", "666666666", 1000.0);
		controller.createBoss("Pepe Boss", "Direcci�n molona", "666666666", 9000.0);
		controller.createBoss("Pepe Boss", "Direcci�n molona", "666666666", 7000.0);
		//para comprobar validaciones salario manager
//		controller.createManager("Pedro Manager", "Direcci�n molona 2", "665266666", 1000.0);
		controller.createManager("Pedro Manager", "Direcci�n molona 2", "665266666", 4000.0);
		//para comprobar validaciones salario Senior
//		controller.createSenior("Laura Employee", "Direcci�n molona 3", "625266666", 500.0);
		controller.createSenior("Laura Employee", "Direcci�n molona 3", "625266666", 3000.0);
		//para comprobar validaciones salario Mid
//		controller.createMid("Peter Employee", "Direcci�n molona 3", "625266666", 3000.0);
		controller.createMid("Peter Employee", "Direcci�n molona 3", "625266666", 2500.0);
		//para comprobar validaciones salario Junior
//		controller.createJunior("Peter Employee", "Direcci�n molona 3", "625266666", 3000.0);
//		controller.createJunior("John Employee", "Direcci�n molona 3", "625266666", 900.0);
		controller.createJunior("John Employee", "Direcci�n molona 3", "625266666", 1500.0);
		//para comprobar validaciones salario voluntarios
//		controller.createVolunteer("Juan Volunteer", "Direcci�n molona 4", "614266666", true, 400);
//		controller.createVolunteer("Juan Volunteer", "Direcci�n molona 4", "614266666", false, 400);
//		controller.createVolunteer("Juan Volunteer", "Direcci�n molona 4", "614266666", false, 200);
//		controller.createVolunteer("Juan Volunteer", "Direcci�n molona 4", "614266666", true, 0);
		controller.createVolunteer("Juan Volunteer", "Direcci�n molona 4", "614266666", true, 200);
		controller.createVolunteer("Juan Volunteer", "Direcci�n molona 4", "614266666", false, 0);
		
	
		//Informaci�n sin aplicar bonus:
		printInfoStaff();
		
		//se aplica el bonus a los empleados:
		controller.setBonus("on");
		
		//Informaci�n aplicando bonus:
		printInfoStaff();		
		
		//se desactiva el bonus comprobando que efectivamente se anula:
		controller.setBonus("off");
		printInfoStaff();
	}
	
	public static void printInfoStaff() throws Exception {
		controller.payAllEmployeers();
		controller.calculateBonus();
		String allEmployees=controller.getAllEmployees();
		System.out.println("EMPLEADOS:\n" + allEmployees + " \n");
	}

}

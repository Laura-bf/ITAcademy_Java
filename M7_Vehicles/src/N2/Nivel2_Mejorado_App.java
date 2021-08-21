package N2;

import java.util.ArrayList;
import java.util.Scanner;

public class Nivel2_Mejorado_App {

	public static void main(String[] args) {
		//Se registra el titular del vehículo
		Owner owner = createOwner();
		
		//Menú para elegir tipo vehículo
		int type = chooseType();
		//Se crea registro según tipo de vehículo con los datos necesarios
		//Se añaden las ruedas necesarias por vehículo
		switch(type) {
			case 1:
				Bike bike = new Bike();
				infoVehicle(bike);
				bike.addTwoWheels(infoTwoWheels("delantera", "trasera"));
				System.out.println(bike);
				break;
			case 2:
				Car car = new Car();
				infoVehicle(car);
				car.addWheels(infoTwoWheels("delantera derecha", "delantera izquierda"), 
								infoTwoWheels("trasera derecha", "trasera izquierda"));
				System.out.println(car);
				break;
			case 3:
				Truck truck = new Truck();
				infoVehicle(truck);
				truck.addWheels(infoTwoWheels("delantera derecha", "delantera izquierda"), 
						infoTwoWheels("trasera derecha", "trasera izquierda"));
				System.out.println(truck);
				break;
			
		}

	}
	//para registrar titular del vehículo
	public static Owner createOwner() {
		ArrayList infoOwner = infoPerson();
		infoOwner.add(infoOwner());
		Owner owner = new Owner((String) infoOwner.get(0), (String) infoOwner.get(1), (Date) infoOwner.get(2), (boolean) infoOwner.get(3), (boolean) infoOwner.get(4)));		
	}
	//para introducir datos de una persona
	public static ArrayList infoPerson(){
		ArrayList infoPerson = new ArrayList();
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre?");
		String name = sc.nextLine();
		infoPerson.add(name);
		
		System.out.println("Apellido?");
		String surname = sc.nextLine();
		infoPerson.add(surname);
		
		System.out.println("Fecha de Nacimiento?");
		String birthDateString = sc.nextLine();
//FALTA FORMATEAR FECHA
//HAY QUE CAMBIARLO PARA AÑADIR LA FECHA FORMATEADA		
		infoPerson.add(birthDateString);
		
		infoLicense();
		System.out.println("Nº del carnet de conducir?");
		String licenseId = sc.nextLine();
//FALTA PEDIR LICENCIA CONDUCIR (a ver cómo, si pidiendo cualquier dato o qué)		
		
		

		
		return infoPerson;
	}
	//para introducir info específica titulares
	public static ArrayList<Boolean> infoOwner(){
		ArrayList<Boolean> infoOwner = new ArrayList<Boolean>();
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Tiene seguro?\n1 - Sí\n2 - No");
		int insurance = sc.nextInt();
		sc.nextLine();
		if(insurance == 1) {
			infoOwner.add(true);
		}else {
			infoOwner.add(false);
		}
		System.out.println("¿Tiene garaje propio?\n1 - Sí\n2 - No");
		int garage = sc.nextInt();
		sc.nextLine();
		if(garage == 1) {
			infoOwner.add(true);
		}else {
			infoOwner.add(false);
		}
	}
	//para elegir tipo vehículo
	public static int chooseType() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Tipo Vehículo:\n1 - Moto\n2 - Coche\n3 - Camión");
		int type = sc.nextInt();
		return type;
	}
	//para introducir datos vehículo
	public static void infoVehicle(Vehicle vehicle) {
		Scanner sc = new Scanner(System.in);
		String plate;
		do {
			System.out.println("Matrícula?");
			plate = sc.nextLine();
		}while(!vehicle.checkPlate(plate));
		vehicle.setPlate(plate);
		System.out.println("Marca?");
		vehicle.setBrand(sc.nextLine());
		System.out.println("Color?");
		vehicle.setColor(sc.nextLine());	
	}
	//para introducir datos ruedas (de dos en dos)
	public static ArrayList<Wheel> infoTwoWheels(String position1, String position2){
		ArrayList<Wheel> twoWheels = new ArrayList<Wheel>();
		Wheel wheel1 = new Wheel();
		Wheel wheel2 = new Wheel();
		double diameter;
		Scanner sc = new Scanner(System.in);
		System.out.println("Marca rueda " + position1 + "?");
		wheel1.setBrand(sc.nextLine());
		do {
			System.out.println("Diámetro rueda " + position1 + "?");
			diameter = sc.nextDouble();
			sc.nextLine();
		} while (!wheel1.checkDiameter(diameter));
		wheel1.setDiameter(diameter);
		twoWheels.add(wheel1);
		
		System.out.println("Marca rueda " + position2 + "?");
		wheel2.setBrand(sc.nextLine());
		do {
			System.out.println("Diámetro rueda " + position2 + "?");
			diameter = sc.nextDouble();
		} while (!wheel2.checkDiameter(diameter));
		wheel2.setDiameter(diameter);
		twoWheels.add(wheel2);
		
		return twoWheels;
	}
}

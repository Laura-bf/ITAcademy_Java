package N2;

import java.util.ArrayList;
import java.util.Scanner;

public class Nivel2_Mejorado_App {

	public static void main(String[] args) {
		//Se registra el titular del veh�culo
		Owner owner = createOwner();
		
		//Men� para elegir tipo veh�culo
		int type = chooseType();
		//Se crea registro seg�n tipo de veh�culo con los datos necesarios
		//Se a�aden las ruedas necesarias por veh�culo
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
	//para registrar titular del veh�culo
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
//HAY QUE CAMBIARLO PARA A�ADIR LA FECHA FORMATEADA		
		infoPerson.add(birthDateString);
		
		infoLicense();
		System.out.println("N� del carnet de conducir?");
		String licenseId = sc.nextLine();
//FALTA PEDIR LICENCIA CONDUCIR (a ver c�mo, si pidiendo cualquier dato o qu�)		
		
		

		
		return infoPerson;
	}
	//para introducir info espec�fica titulares
	public static ArrayList<Boolean> infoOwner(){
		ArrayList<Boolean> infoOwner = new ArrayList<Boolean>();
		Scanner sc = new Scanner(System.in);
		System.out.println("�Tiene seguro?\n1 - S�\n2 - No");
		int insurance = sc.nextInt();
		sc.nextLine();
		if(insurance == 1) {
			infoOwner.add(true);
		}else {
			infoOwner.add(false);
		}
		System.out.println("�Tiene garaje propio?\n1 - S�\n2 - No");
		int garage = sc.nextInt();
		sc.nextLine();
		if(garage == 1) {
			infoOwner.add(true);
		}else {
			infoOwner.add(false);
		}
	}
	//para elegir tipo veh�culo
	public static int chooseType() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Tipo Veh�culo:\n1 - Moto\n2 - Coche\n3 - Cami�n");
		int type = sc.nextInt();
		return type;
	}
	//para introducir datos veh�culo
	public static void infoVehicle(Vehicle vehicle) {
		Scanner sc = new Scanner(System.in);
		String plate;
		do {
			System.out.println("Matr�cula?");
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
			System.out.println("Di�metro rueda " + position1 + "?");
			diameter = sc.nextDouble();
			sc.nextLine();
		} while (!wheel1.checkDiameter(diameter));
		wheel1.setDiameter(diameter);
		twoWheels.add(wheel1);
		
		System.out.println("Marca rueda " + position2 + "?");
		wheel2.setBrand(sc.nextLine());
		do {
			System.out.println("Di�metro rueda " + position2 + "?");
			diameter = sc.nextDouble();
		} while (!wheel2.checkDiameter(diameter));
		wheel2.setDiameter(diameter);
		twoWheels.add(wheel2);
		
		return twoWheels;
	}
}

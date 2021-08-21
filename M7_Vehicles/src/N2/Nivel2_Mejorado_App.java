package N2;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author laura
 * Para esta mejora he tenido que crear getters y setters en vehicle, 
 * constructores por defecto en Vehicle y sus subclases,
 * la comprobación de matrícula ahora es un método de la clase Vehicle.
 * También he puesto como abstracto el método addTwhoWheels(wheels) en Vehicle.
 */
public class Nivel2_Mejorado_App {

	public static void main(String[] args) {
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

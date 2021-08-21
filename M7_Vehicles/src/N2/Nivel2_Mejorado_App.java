package N2;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author laura
 * Para esta mejora he tenido que crear getters y setters en vehicle, 
 * constructores por defecto en Vehicle y sus subclases,
 * la comprobaci�n de matr�cula ahora es un m�todo de la clase Vehicle.
 * Tambi�n he puesto como abstracto el m�todo addTwhoWheels(wheels) en Vehicle.
 */
public class Nivel2_Mejorado_App {

	public static void main(String[] args) {
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

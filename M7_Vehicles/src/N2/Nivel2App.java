package N2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Nivel2App {

	public static void main(String[] args) {
		//Men� para elegir tipo veh�culo
		int type = chooseType();
		//Se crea registro seg�n el tipo de veh�culo despu�s de pedir los datos que son comunes a todos los veh�culos
		//Luego se pide info y se a�aden las ruedas seg�n tipo de veh�culo.
		switch(type) {
			case 1:
				Bike bike = createBike();
				bike.addWheels(createWheel(infoWheel("delantera")), createWheel(infoWheel("trasera")));
				System.out.println(bike);
				break;
			case 2:
				Car car = createCar();
				car.addWheels(createTwoWheels("delanteras"), createTwoWheels("traseras"));
				System.out.println(car);
				break;
			case 3: 
				Truck truck = createTruck();
				truck.addWheels(createTwoWheels("delanteras"), createTwoWheels("traseras"));
				System.out.println(truck);
				break;
			default:
				System.out.println("ERROR - Opci�n inexistente");
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
	
	//para pedir info compartida veh�culos
	public static ArrayList<String> infoVehicle(){
		ArrayList<String> infoVehicle = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		String plate = "";
		boolean validPlate = false;
		do {
			System.out.println("Matr�cula?");
			plate = sc.nextLine();
			validPlate = checkPlate(plate);
		}while(!validPlate);
		infoVehicle.add(plate);
		System.out.println("Marca?");
		String brand = sc.nextLine();
		infoVehicle.add(brand);
		System.out.println("Color?");
		String color = sc.nextLine();
		infoVehicle.add(color);
				
		return infoVehicle;
	}
	
	//para comprobar matr�cula
	public static boolean checkPlate(String plate) {
		boolean validPlate = true;
		if(plate.length()>7) {
			System.err.println("M�tricula no v�lida. Supera el m�ximo de 7 carateres");
			validPlate = false;
		} else {
			for(int i=0; i<4; i++) {
				if(!Character.isDigit(plate.charAt(i))) {
					System.err.println("M�tricula no v�lida. Obligatorio: 4 n�meros + 2 � 3 letras");
					i=4;
					validPlate = false;
				} 
			}
			if(validPlate) {
				String plateLetters = plate.substring(4);
				for(int i=plateLetters.length()-1; i>=0; i--) {
					if(Character.isDigit(plateLetters.charAt(i))){
						System.err.println("M�tricula no v�lida. Obligatorio: 4 n�meros + 2 � 3 letras");
						i=0;
						validPlate = false;
					}		
				}
			}
		}
		return validPlate;
	}
	
	//para crear registro MOTO
	public static Bike createBike() {
		ArrayList<String> infoVehicle = infoVehicle();
		Bike bike = new Bike(infoVehicle.get(0), infoVehicle.get(1), infoVehicle.get(2));
		return bike;
	}
	
	//para crear registro COCHE
	public static Car createCar() {
		ArrayList<String> infoVehicle = infoVehicle();
		Car car = new Car(infoVehicle.get(0), infoVehicle.get(1), infoVehicle.get(2));
		return car;
	}
	
	//para crear registro CAMI�N
	public static Truck createTruck() {
		ArrayList<String> infoVehicle = infoVehicle();
		Truck truck = new Truck(infoVehicle.get(0), infoVehicle.get(1), infoVehicle.get(2));
		return truck;
	}
	
	//para pedir info de una rueda
	public static ArrayList infoWheel(String side){
		ArrayList infoWheel = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Marca rueda "+ side);
		String brand = sc.nextLine();
		infoWheel.add(brand);
		double diameter;
		do {
			System.out.println("Di�metro rueda " + side);
			diameter = sc.nextDouble();
			if(diameter <= 0.4 || diameter >=4) {
				System.err.println("ERROR - Di�metros permitidos entre 0.4 y 4");
			}
		}while(diameter <= 0.4 || diameter >=4);
		infoWheel.add(diameter);
		return infoWheel;
	}
	
	//para crear una rueda
	public static Wheel createWheel(ArrayList infoWheel) {
		Wheel wheel = new Wheel((String) infoWheel.get(0), (double) infoWheel.get(1));
		return wheel;
	}
	
	//para crear un par de ruedas
	public static List<Wheel> createTwoWheels(String axle) {
		List<Wheel> twoWheels = new ArrayList<Wheel>();
		System.out.print("Ruedas "+ axle + ": ");
		twoWheels.add(createWheel(infoWheel("DERECHA")));
		twoWheels.add(createWheel(infoWheel("IZQUIERDA")));
		return twoWheels;
	}
}

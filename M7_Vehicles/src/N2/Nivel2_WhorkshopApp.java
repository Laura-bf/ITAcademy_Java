package N2;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Nivel2_WhorkshopApp {

	public static void main(String[] args) {
		//Se registra el titular del vehículo
		Owner owner = createOwner();
		//Se registra la licencia conducir del titular
		License ownerLicense = createLicense(owner);
		owner.setLicense(ownerLicense);
		//Menú para elegir tipo vehículo
		int type = 0;
		try {
			type = chooseType(ownerLicense.getLicenseType());
		} catch (Exception e) {
			System.err.println("Carnet no válido para este tipo de vehículo");
			System.out.println("Finalizando programa....");
			System.out.println("Ningún vehículo ha sido registrado a nombre de este titular\n"+ owner);
		}	
		//Se crea registro según tipo de vehículo con los datos necesarios
		//Se añaden las ruedas necesarias por vehículo
		switch(type) {
			case 1:		
				//Se crea registro moto
				Bike bike = new Bike();
				infoVehicle(bike);
				//Se añade titular a la moto
				bike.setOwner(owner);
				//Se añaden las ruedas al registro
				bike.addTwoWheels(infoTwoWheels("delantera", "trasera"));
				//Se añade conductor a la moto
				try {
					Driver driver = createDriver(owner, bike, Bike.getLicense());
					bike.setDriver(driver);
				} catch (Exception e) {
					System.err.println("Carnet no válido para este tipo de vehículo");
					System.out.println("Finalizando programa....");
					System.out.println("Ningún vehículo ha sido registrado a nombre de este titular");
				}
				//Se muestra registro creado
				System.out.println(bike);
				break;
			case 2:
				Car car = new Car();
				infoVehicle(car);
				car.setOwner(owner);
				car.addWheels(infoTwoWheels("delantera derecha", "delantera izquierda"), 
							infoTwoWheels("trasera derecha", "trasera izquierda"));
				try {
					Driver driver = createDriver(owner, car, Car.getLicense());
					car.setDriver(driver);
				} catch (Exception e) {
					System.err.println("Carnet no válido para este tipo de vehículo");
					System.out.println("Finalizando programa....");
					System.out.println("Ningún vehículo ha sido registrado a nombre de este titular");
				}
				System.out.println(car);
				break;
			case 3:
				Truck truck = new Truck();
				infoVehicle(truck);
				truck.setOwner(owner);
				truck.addWheels(infoTwoWheels("delantera derecha", "delantera izquierda"), 
							infoTwoWheels("trasera derecha", "trasera izquierda"));
				try {
					Driver driver = createDriver(owner, truck, Truck.getLicense());
					truck.setDriver(driver);
				} catch (Exception e) {
					System.err.println("Carnet no válido para este tipo de vehículo");
					System.out.println("Finalizando programa....");
					System.out.println("Ningún vehículo ha sido registrado a nombre de este titular");
				}
				System.out.println(truck);
				break;
			case 4:
				System.out.println("Finalizando programa....");
				System.out.println("Ningún vehículo ha sido registrado a nombre de este titular");
		}
	}

	//para crear titular del vehículo
	public static Owner createOwner() {
		System.out.println("Introduce los datos del titular del vehículo");
		ArrayList<Object> infoOwner = infoPerson();
		Owner owner = new Owner((String) infoOwner.get(0), (String) infoOwner.get(1), (Date) infoOwner.get(2));
		askOwner(owner);
		
		return owner;
	}
	//para crear conductor del vehículo
	public static Driver createDriver(Owner owner, Vehicle vehicle, int licenseType) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("El titular será el conductor del vehículo?\n1 - Sí\n2 - No");
		int option = sc.nextInt();
		Driver driver;
		if(option==1) {
			driver = new Driver(owner.getName(), owner.getSurname(), owner.getBirthDate());
			driver.setLicense(driver.createLicense(owner.getLicense().getLicenseId(), owner.getLicense().getLicenseType(), owner.getLicense().getExpiryDate()));
		} else {
			System.out.println("Introducir los datos del conductor del vehículo");
			ArrayList<Object> infoDriver = infoPerson();
			driver = new Driver((String) infoDriver.get(0), (String) infoDriver.get(1), (Date) infoDriver.get(2));
			License driverLicense = createLicense(driver);
			
			if(driverLicense.getLicenseType()==licenseType) {
				driver.setLicense(driverLicense);
			} else {
				throw new Exception("Carnet no válido para este tipo de vehículo");
			}	
		}
		
		return driver;
	}
	
	//para introducir datos de una persona
	public static ArrayList<Object> infoPerson(){
		ArrayList<Object> infoPerson = new ArrayList<Object>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Nombre?");
		String name = sc.nextLine().toUpperCase();
		infoPerson.add(name);
		
		System.out.println("Apellido?");
		String surname = sc.nextLine().toUpperCase();
		infoPerson.add(surname);
		
		System.out.println("Fecha de Nacimiento?");
		Date birthDate = infoDate();
		infoPerson.add(birthDate);
		
	
		return infoPerson;
	}
	//para introducir info específica titulares (seguro y garaje)
	public static void askOwner(Owner owner){
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Tiene seguro?\n1 - Sí\n2 - No");
		int insurance = sc.nextInt();
		sc.nextLine();
		if(insurance == 1) {
			owner.setHasInsurance(true);
		}else {
			owner.setHasInsurance(false);
		}
		System.out.println("¿Tiene garaje propio?\n1 - Sí\n2 - No");
		int garage = sc.nextInt();
		if(garage == 1) {
			owner.setHasGarage(true);
		}else {
			owner.setHasGarage(false);
		}
	}
	//para crear fechas
		public static Date infoDate() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Día?");
			int day = sc.nextInt();
			sc.nextLine();
			System.out.println("Mes?");
			int month = sc.nextInt();
			month -= 1;
			sc.nextLine();
			System.out.println("Año?");
			int year = sc.nextInt();
			year -= 1900;
			
			Date date = new Date(year, month, day);
					
			return date;
		}
	//para crear un carnet de conducir
	public static License createLicense(Person person) {
		ArrayList<Object> infoLicense = infoLicense();
		License license = person.createLicense((String) infoLicense.get(0), 
								(int) infoLicense.get(1), (Date) infoLicense.get(2));
		return license;
	}
	//para introducir datos de un carnet de conducir
	public static ArrayList<Object> infoLicense(){
		ArrayList<Object> infoLicense = new ArrayList<Object>();
		Scanner sc = new Scanner(System.in);
		boolean validId = false;
		String licenseId;
		do {
			System.out.println("Número del carnet de conducir (Ej: 00000000X)");
			licenseId = sc.nextLine();
			validId = License.checkLicenseId(licenseId);
		}while(!validId);
		infoLicense.add(licenseId);
		System.out.println("Tipo de carnet:\n1 - para tipo A (Moto)\n2 - para tipo B (Coche)\n3 - para tipo C (Camión)");
		int licenseType = sc.nextInt();
		infoLicense.add(licenseType);
		System.out.println("Fecha fin validez carnet?");
		Date expiryDate = infoDate();
		infoLicense.add(expiryDate);
		return infoLicense;	
	}
	
	//para elegir tipo vehículo
	public static int chooseType(int licenseType) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Tipo Vehículo:\n1 - Moto\n2 - Coche\n3 - Camión\n4 - Salir");
		int type = sc.nextInt();
		if(type != licenseType) {
			throw new Exception();
		}
		
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

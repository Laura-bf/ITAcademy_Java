package N3;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class N3_App {

	public static void main(String[] args) {
		ArrayList<Person> users = new ArrayList<Person>();
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		boolean end = false;
		int start;
		int option;
		do {
			start = startMenu();
			switch (start) {
				case 1:
					option = menuVehicleType();
					if(option!=4) {
						createVehicle(option, users, vehicles);
					}
					break;
				case 2:
					option = menuUserType();
					if(option!=3) {
						createUser(option, users, vehicles);
					}
					break;
				case 3:
					System.out.println("Saliendo de la aplicación....");
					System.out.println("Play para volver a iniciar");
					end = true;
					break;
				default:
					System.out.println("Error - Elija una opción válida (1-2-3)");
					break;
			}
		}while(!end);
		
		//para comprobar que se fue añadiendo todo a las listas de usuarios y de vehículos
		System.out.println(users.toString());
		System.out.println(vehicles.toString());
	}
	//Menú Inicio
	public static int startMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("INICIO:\n1 - Vehículos\n2 - Usuarios\n3 - Salir");
		return sc.nextInt();
	}
	//Menú Elegir Tipo Vehículo
	public static int menuVehicleType() {
		Scanner sc = new Scanner(System.in);
		System.out.println("TIPO DE VEHÍCULO:\n1 - Moto\n2 - Coche\n3 - Camión\n4 - Volver a Inicio");
		return sc.nextInt();
	}
	//Menú Elegir Tipo Usuario
	public static int menuUserType() {
		Scanner sc = new Scanner(System.in);
		System.out.println("TIPO DE USUARIO:\n1 - Titular\n2 - Conductor\n3 - Volver a Inicio");
		return sc.nextInt();
	}
	//Menú Añadir Conductores
	public static boolean menuAddDrivers() {
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Añadir conductores para este vehículo?\n1 - Sí\n2 - No");
		int option = sc.nextInt();
		if(option == 1)
			return true;
		else
			return false;
	}
	//Crear Usuarios
	public static void createUser(int userType, ArrayList<Person> users, ArrayList<Vehicle> vehicles) {
		switch (userType) {
			case 1:
				Owner owner = createOwner(users);
				createVehicle(owner, owner.getLicenseType(), users, vehicles);
				break;
			case 2:
				createDriver(users);
				break;
			default:
				System.out.println("Opción inexistente. Volviendo al Inicio....");
				break;
		}
	}
	//Crear Titular (sin vehiculo registrado)
	public static Owner createOwner(ArrayList<Person> users) {
		System.out.println("Introduzca información del TITULAR");
		ArrayList<Object> infoOwner = infoPerson();
		Owner owner = new Owner((String) infoOwner.get(0), (String) infoOwner.get(1), (Date) infoOwner.get(2));
		infoOwner(owner);
		License ownerLicense = createLicense(owner);
		owner.setLicense(ownerLicense);
		users.add(owner);
		return owner;
	}
	//Crear Titular (para un vehiculo ya registrado)
	public static void createOwner(ArrayList<Person> users, Vehicle vehicle) {
		System.out.println("Introduzca información del TITULAR");
		ArrayList<Object> infoOwner = infoPerson();
		Owner owner = new Owner((String) infoOwner.get(0), (String) infoOwner.get(1), (Date) infoOwner.get(2));
		infoOwner(owner);
		License ownerLicense = createLicense(owner);
		owner.setLicense(ownerLicense);
		users.add(owner);
		vehicle.setOwner(owner);
	}
	//Crear Conductor (sin vehiculo registrado)
	public static Driver createDriver(ArrayList<Person> users) {
		System.out.println("Introduzca información del CONDUCTOR");
		ArrayList<Object> infoDriver = infoPerson();
		Driver driver = new Driver((String) infoDriver.get(0), (String) infoDriver.get(1), (Date) infoDriver.get(2));
		License driverLicense = createLicense(driver);
		driver.setLicense(driverLicense);
		users.add(driver);
		return driver;
	}
	//Crear Conductor (para un vehiculo ya registrado)
	public static void createDriver(ArrayList<Person> users, Vehicle vehicle) {
		System.out.println("Introduzca información del CONDUCTOR");
		ArrayList<Object> infoDriver = infoPerson();
		Driver driver = new Driver((String) infoDriver.get(0), (String) infoDriver.get(1), (Date) infoDriver.get(2));
		License driverLicense = createLicense(driver);
		driver.setLicense(driverLicense);
		if(vehicle.checkLicenseType(driver.getLicenseType())) {
			vehicle.addDriver(driver);
		} else {
			System.out.println("Es necesario añadir un conductor con un carnet válido para el vehículo registrado");
			createDriver(users, vehicle);
		}
		users.add(driver);
	}
	//Crear Vehículos
	public static void createVehicle(int vehicleType, ArrayList<Person> users, ArrayList<Vehicle> vehicles) {
		switch (vehicleType) {
			case 1:
				Bike bike = createBike(users, vehicles);
				createOwner(users, bike);
				if(menuAddDrivers()) {
					createDriver(users, bike);
				}
				vehicles.add(bike);
				break;
			case 2:
				Car car = createCar(users, vehicles);
				createOwner(users, car);
				if(menuAddDrivers()) {
					createDriver(users, car);
				}
				vehicles.add(car);
				break;
			case 3:
				Truck truck = createTruck(users, vehicles);
				createOwner(users, truck);
				if(menuAddDrivers()) {
					createDriver(users, truck);
				}
				vehicles.add(truck);
				break;
			case 4:
				break;
			default:
				System.out.println("Opción inexistente. Volviendo al Inicio....");
				break;
		}
	}
	//Crear Vehículos para un Titular ya registrado
	public static void createVehicle(Owner owner, int vehicleType, ArrayList<Person> users, ArrayList<Vehicle> vehicles) {
		switch (vehicleType) {
			case 1:
				Bike bike = createBike(users, vehicles);
				bike.setOwner(owner);
				if(!bike.checkLicenseType(owner.getLicenseType())) {
					System.out.println("Este titular no puede conducir motos. Añadir un conductor con un carnet válido para el vehículo registrado");
					createDriver(users, bike);
				}else if(menuAddDrivers()) {
					createDriver(users, bike);
				}
				vehicles.add(bike);
				break;
			case 2:
				Car car = createCar(users, vehicles);
				car.setOwner(owner);
				if(!car.checkLicenseType(owner.getLicenseType())) {
					System.out.println("Este titular no puede conducir coches. Añadir un conductor con un carnet válido para el vehículo registrado");
					createDriver(users, car);
				}else if(menuAddDrivers()) {
					createDriver(users, car);
				}
				vehicles.add(car);
				break;
			case 3:
				Truck truck = createTruck(users, vehicles);
				truck.setOwner(owner);
				if(!truck.checkLicenseType(owner.getLicenseType())) {
					System.out.println("Este titular no puede conducir camiones. Añadir un conductor con un carnet válido para el vehículo registrado");
					createDriver(users, truck);
				}else if(menuAddDrivers()) {
					createDriver(users, truck);
				}
				vehicles.add(truck);
				break;
			case 4:
				break;
			default:
				System.out.println("Opción inexistente. Volviendo al Inicio....");
				break;
		}
	}
	//Crear Moto
	public static Bike createBike(ArrayList<Person> users, ArrayList<Vehicle> vehicles){
		Bike bike = new Bike();
		System.out.println("Introduzca datos MOTO");
		infoVehicle(bike);
		bike.addTwoWheels(infoTwoWheels("delantera", "trasera"));
		return bike;
	}
	//Crear Coche
	public static Car createCar(ArrayList<Person> users, ArrayList<Vehicle> vehicles){
		Car car = new Car();
		System.out.println("Introduzca datos COCHE");
		infoVehicle(car);
		car.addWheels(infoTwoWheels("delantera derecha", "delantera izquierda"), 
					infoTwoWheels("trasera derecha", "trasera izquierda"));
		return car;	
	}
	//Crear Camión
	public static Truck createTruck(ArrayList<Person> users, ArrayList<Vehicle> vehicles){
		Truck truck = new Truck();
		System.out.println("Introduzca datos CAMIÓN");
		infoVehicle(truck);
		truck.addWheels(infoTwoWheels("delantera derecha", "delantera izquierda"), 
					infoTwoWheels("trasera derecha", "trasera izquierda"));
		return truck;
	}
	//Crear un carnet de conducir
	public static License createLicense(Person person) {
		System.out.println("Introduzca información del CARNET DE CONDUCIR");
		ArrayList<Object> infoLicense = infoLicense();
		License license = person.createLicense((String) infoLicense.get(0), 
								(int) infoLicense.get(1), (Date) infoLicense.get(2));
		return license;
	}
	//Crear fechas
	public static Date createDate() {
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
//INPUTS DATOS:
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
		Date birthDate = createDate();
		infoPerson.add(birthDate);
	
		return infoPerson;
	}
	//para introducir info específica titulares (seguro y garaje)
	public static void infoOwner(Owner owner){
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
			Date expiryDate = createDate();
			infoLicense.add(expiryDate);
			return infoLicense;	
		}
}

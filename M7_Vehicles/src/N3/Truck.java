package N3;

import java.util.List;

public class Truck extends Vehicle {
	
	private final static int LICENSE = 3;
	
	public Truck() {
	}
	
	public Truck(String plate, String brand, String color) {
		super(plate, brand, color);
	}
	
	public static int getLicense() {
		return LICENSE;
	}
	
	public boolean checkLicenseType(int licenseType) {
		if(licenseType==LICENSE) {
			return true;
		} else {
			System.out.println("Carnet no válido para este tipo de vehículos");
			return false;
		}
	}
	
	public void addDriver(Driver driver) {
		if(checkLicenseType(driver.getLicenseType())) {
			this.drivers.add(driver);
		}
	}
	
	public void addWheels(List<Wheel> frontWheels, List<Wheel> backWheels){
		addTwoWheels(frontWheels);
		addTwoWheels(backWheels);
	}
	
	public void addTwoWheels(List<Wheel> wheels){
		if (wheels.size() != 2)
			System.err.println("Máximo: 2 ruedas por eje");
			
		Wheel rightWheel = wheels.get(0);
		Wheel leftWheel = wheels.get(1);

		if(rightWheel.equals(leftWheel)) {
			this.wheels.add(leftWheel);
			this.wheels.add(rightWheel);
		} else {
			this.wheels.clear();
			System.err.println("RUEDAS NO REGISTRADAS - Las ruedas de un mismo eje, delantero o trasero, deben ser iguales");
		}
	}

	@Override
	public String toString() {
		return "Truck:\nplate=" + plate + ", brand=" + brand + ", color=" + color + "\nWheels= " + wheels + "\nOwner:\n"+ owner + "\nDrivers:\n" + drivers;
	}
}

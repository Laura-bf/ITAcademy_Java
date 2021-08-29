package N3;

import java.util.List;

public class Bike extends Vehicle {
	
	private final static int LICENSE = 1;
	
	public Bike() {
	}
	
	public Bike(String plate, String brand, String color) {
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
	
	public void addTwoWheels(List<Wheel> wheels) {
		this.wheels.add(wheels.get(0));
		this.wheels.add(wheels.get(1));
	}
	
	public void addTwoWheels(Wheel frontWheel, Wheel backWheel) {
		this.wheels.add(frontWheel);
		this.wheels.add(backWheel);
	}
	
	@Override
	public String toString() {
		return "Bike:\nplate=" + plate + ", brand=" + brand + ", color=" + color + "\nWheels= " + wheels + "\nOwner:\n"+ owner + "\nDrivers:\n" + drivers;
	}
}
package N3;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
	
	protected String plate;
	protected String brand;
	protected String color;
	
	protected List<Wheel> wheels = new ArrayList<Wheel>();
	
	protected Owner owner;
	protected ArrayList<Driver> drivers = new ArrayList<Driver>();
	
	public Vehicle() {
	}
	
	public Vehicle(String plate, String brand, String color) {
		this.plate = plate;
		this.brand = brand;
		this.color = color;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setPlate(String plate) {
		if(checkPlate(plate)) {
			this.plate = plate;
		}
	}
	
	public Owner getOwner() {
		return owner;
	}
	public void setOwner(Owner owner) {
		if(checkLicenseType(owner.getLicenseType())) {
			this.owner = owner;
		}
	}

	public ArrayList<Driver> getDrivers() {
		return drivers;
	}
	
	public void addDriver(Driver driver) {
		if(checkLicenseType(driver.getLicenseType())) {
			this.drivers.add(driver);
		}
	}
	
	public List<Wheel> getWheels() {
		return wheels;
	}
	
	public boolean checkPlate(String plate) {
		boolean validPlate = true;
		if(plate.length()>7 || plate.length()<6) {
			System.err.println("Mátricula no válida. Obligatorio: 6 ó 7 caracteres");
			validPlate = false;
		} else {
			for(int i=0; i<4; i++) {
				if(!Character.isDigit(plate.charAt(i))) {
					System.err.println("Mátricula no válida. Obligatorio: 4 números + 2 ó 3 letras");
					i=4;
					validPlate = false;
				} 
			}
			if(validPlate) {
				String plateLetters = plate.substring(4);
				for(int i=plateLetters.length()-1; i>=0; i--) {
					if(Character.isDigit(plateLetters.charAt(i))){
						System.err.println("Mátricula no válida. Obligatorio: 4 números + 2 ó 3 letras");
						i=0;
						validPlate = false;
					}		
				}
			}
		}
		return validPlate;
	}
	
	public abstract boolean checkLicenseType(int licenseType);
	public abstract void addTwoWheels(List<Wheel> wheels);
	
	@Override
	public String toString() {
		return "Vehicle [plate=" + plate + ", brand=" + brand + ", color=" + color + ", wheels=" + wheels + ", owner=" + owner + ", Drivers=" + drivers +"]";
	}
}

package N2;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {

	protected String plate;
	protected String brand;
	protected String color;
	
	protected List<Wheel> wheels = new ArrayList<Wheel>();
	protected Owner owner;
	protected Driver driver;
	
	public Vehicle() {
	}
	
	public Vehicle(String plate, String brand, String color) {
		this.plate = plate;
		this.brand = brand;
		this.color = color;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		if(checkPlate(plate)) {
			this.plate = plate;
		}
	}

	public List<Wheel> getWheels() {
		return wheels;
	}
	
	public Owner getOwner() {
		return owner;
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	public Driver getDriver() {
		return driver;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
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
	
	public abstract void addTwoWheels(List<Wheel> wheels);

	@Override
	public String toString() {
		return "Vehicle [plate=" + plate + ", brand=" + brand + ", color=" + color + ", wheels=" + wheels + "]";
	}

	
}

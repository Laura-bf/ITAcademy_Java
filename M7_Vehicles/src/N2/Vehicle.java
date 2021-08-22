package N2;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {

	/**
	 * Matrícula del vehículo 
	 */
	protected String plate;
	/**
	 * Marca del vehículo
	 */
	protected String brand;
	/**
	 * Color del vehículo
	 */
	protected String color;
	/**
	 * Ruedas del vehículo
	 */
	protected List<Wheel> wheels = new ArrayList<Wheel>();
	/**
	 * Titular del vehículo
	 */
	protected Owner owner;
	/**
	 * Conductor del vehículo
	 */
	protected Driver driver;
	
	/**
	 * Constructor por defecto
	 */
	public Vehicle() {
	}
	/**
	 * Constructor con 3 parámetros
	 * @param plate
	 * @param brand
	 * @param color
	 */
	public Vehicle(String plate, String brand, String color) {
		this.plate = plate;
		this.brand = brand;
		this.color = color;
	}
	/**
	 * Devuelve la marca del vehículo
	 * @return brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * Modifica la marca de un vehículo
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * Devuelve el color de un vehículo
	 * @return color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * Modifica el color de un vehículo
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * Devuelve la matrícula de un vehículo
	 * @return plate
	 */
	public String getPlate() {
		return plate;
	}
	/**
	 * Modifica la matrícula de un vehículo
	 * @param plate
	 */
	public void setPlate(String plate) {
		if(checkPlate(plate)) {
			this.plate = plate;
		}
	}
	/**
	 * Devuelve la lista con las ruedas que tiene un vehículo
	 * @return List<Wheel>
	 */
	public List<Wheel> getWheels() {
		return wheels;
	}
	
	/**
	 * @return the owner
	 */
	public Owner getOwner() {
		return owner;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	/**
	 * @return the driver
	 */
	public Driver getDriver() {
		return driver;
	}
	/**
	 * @param driver the driver to set
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	/**
	 * Método para comprobar que la matrícula es válida
	 * @param plate
	 */
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
	/**
	 * Método abstracto para añadir un par de ruedas según tipo vehículo
	 * @param wheels (par de ruedas según tipo vehículo)
	 */
	public abstract void addTwoWheels(List<Wheel> wheels);

	@Override
	public String toString() {
		return "Vehicle [plate=" + plate + ", brand=" + brand + ", color=" + color + ", wheels=" + wheels + "]";
	}

	
}
